package pl.umk.mat.locals.services

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken

import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.*
import pl.umk.mat.locals.exceptions.ResourceAlreadyExistException
import pl.umk.mat.locals.exceptions.UserAuthException
import pl.umk.mat.locals.models.TemporaryUser
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.repositories.TemporaryUserRepository
import pl.umk.mat.locals.repositories.UserRepository
import pl.umk.mat.locals.security.JwtTokenProvider
import javax.security.auth.message.AuthException

import javax.transaction.Transactional


@Service
class UserService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider,
        private val temporaryUserRepository: TemporaryUserRepository
) {

    fun localLogin(loginRequest: LoginRequest): AuthResponse {
        val user = userRepository.findUserByEmail(loginRequest.email) ?: throw UserAuthException("User not found.")
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password))
        return createAuthResponse(user)
    }

    fun googleLogin(googleCode: GoogleCode): AuthResponse {
        val googleAccountInfo = getGoogleAccountInfo(googleCode.code)
        val user = userRepository.findUserByGoogleId(googleAccountInfo.subject) ?: throw UserAuthException("User not found.")
        return createAuthResponse(user)
    }

    @Transactional
    fun localRegister(registerRequest: RegisterRequest): AuthResponse {

        if (userRepository.existsUserByEmail(registerRequest.email))
            throw ResourceAlreadyExistException("User with this email already exist.")

        val newUser = userRepository.save(User(
                password = passwordEncoder.encode(registerRequest.password),
                email = registerRequest.email,
                firstName = registerRequest.firstName,
                lastName = registerRequest.lastName,
                country = registerRequest.country
        ))

        return createAuthResponse(newUser)
    }

    @Transactional
    fun googleConfirmRegister(confirmGoogleAccount: ConfirmGoogleAccount, token: String): AuthResponse {
        val jwtTokenPayload = jwtTokenProvider.getTokenPayloadFromTokenForTemporaryUser(token.removePrefix("Bearer "))
        val temporaryUserId = jwtTokenPayload.claims["userId"]?.asLong() ?: throw UserAuthException("Bad token.")

        if (userRepository.existsUserByEmail(confirmGoogleAccount.email))
            throw ResourceAlreadyExistException("User with this email already exist.")

        if (userRepository.existsUserByGoogleId(jwtTokenPayload.subject))
            throw ResourceAlreadyExistException("User with this GoogleId already exist.")

        val newUser = userRepository.save(User(
                email = confirmGoogleAccount.email,
                firstName = confirmGoogleAccount.firstName,
                lastName = confirmGoogleAccount.lastName,
                country = confirmGoogleAccount.country,
                googleId = jwtTokenPayload.subject
        ))

        temporaryUserRepository.deleteById(temporaryUserId)

        return createAuthResponse(newUser)
    }


    @Transactional
    fun googleRegister(googleCode: GoogleCode): GoogleAccountInfo {
        val payload = getGoogleAccountInfo(googleCode.code)

        if (userRepository.existsUserByGoogleId(payload.subject))
            throw ResourceAlreadyExistException("User with this GoogleId already exist.")

        val temporaryUser = createTemporaryGoogleUserIfNotExist(payload)
        val token = jwtTokenProvider.createTokenForTemporaryUser(temporaryUser.googleId, temporaryUser.id)

        return GoogleAccountInfo(
                email = temporaryUser.email,
                firstName = temporaryUser.firstName,
                lastName = temporaryUser.lastName,
                country = temporaryUser.country,
                authorizationToken = token
        )
    }

    @Transactional
    fun connectGoogleAccount(googleCode: GoogleCode, user: User) {
        val payload = getGoogleAccountInfo(googleCode.code)

        if (userRepository.existsUserByGoogleId(payload.subject))
            throw ResourceAlreadyExistException("User with this GoogleId already exist.")

        temporaryUserRepository.deleteTemporaryUsersByGoogleId(payload.subject)

        userRepository.save(user.copy(
                googleId = payload.subject
        ))
    }

    private fun createAuthResponse(user: User): AuthResponse {
        return AuthResponse(jwtTokenProvider.createToken(user.email, user.id))
    }

    fun findUserByEmail(email: String): User? {
        return userRepository.findUserByEmail(email)
    }

    private fun getGoogleAccountInfo(code: String): GoogleIdToken.Payload {
        return GoogleAuthorizationCodeTokenRequest(
                NetHttpTransport(),
                JacksonFactory(),
                "1095850462503-47vu62eqij6r2r8k1ucugo34gdc5dide.apps.googleusercontent.com",
                "ERge9vjfR2Xks0C19Mz2xny9",
                code,
                "http://localhost:9615"
        ).execute().parseIdToken().payload ?: throw AuthException("Error receiving data from google.")
    }


    private fun createTemporaryGoogleUserIfNotExist(googleTokenResponse: GoogleIdToken.Payload): TemporaryUser {
        return temporaryUserRepository.findTemporaryUserByGoogleId(googleTokenResponse.subject) ?: temporaryUserRepository.save(TemporaryUser(
                firstName = googleTokenResponse["given_name"] as String,
                lastName = googleTokenResponse["family_name"] as String,
                googleId = googleTokenResponse.subject,
                country = googleTokenResponse["locale"] as String,
                email = googleTokenResponse.email
        ))
    }

    fun getSelfUserInfo(user: User): UserSelfInfo {
        return UserSelfInfo(user)
    }
}