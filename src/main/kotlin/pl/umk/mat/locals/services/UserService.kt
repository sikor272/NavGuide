package pl.umk.mat.locals.services

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken

import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.*
import pl.umk.mat.locals.exceptions.UserAuthException
import pl.umk.mat.locals.models.Role
import pl.umk.mat.locals.models.TemporaryUser
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.repositories.TemporaryUserRepository
import pl.umk.mat.locals.repositories.UserRepository
import pl.umk.mat.locals.security.JwtTokenProvider

import java.util.*
import javax.transaction.Transactional


@Service
class UserService(
        private val userRepository: UserRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider,
        private val temporaryUserRepository: TemporaryUserRepository
) {

    fun localLogin(loginRequest: LoginRequest): AuthResponse {
        val user = userRepository.findUserByEmail(loginRequest.email) ?: throw UserAuthException("User not found")
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password))
        return createAuthResponse(user)
    }

    fun googleLogin(googleCode: GoogleCode): AuthResponse {
        val googleAccountInfo = getGoogleAccountInfo(googleCode.code)
        val user = userRepository.findUserByGoogleId(googleAccountInfo.subject)
                ?: throw UserAuthException("User not found")
        return createAuthResponse(user)
    }

    @Transactional
    fun localRegister(registerRequest: RegisterRequest): AuthResponse {

        //TODO sprawdznie czy wszystko jest unikalne

        val newUser = userRepository.save(User(
                password = bCryptPasswordEncoder.encode(registerRequest.password),
                role = Role.TRAVELER,
                email = registerRequest.email
        ))

        return createAuthResponse(newUser)
    }

    @Transactional
    fun googleConfirmRegister(confirmGoogleAccount: ConfirmGoogleAccount, token: String): AuthResponse {
        val jwtTokenPayload = jwtTokenProvider.getTokenPayloadFromTokenForTemporaryUser(token.removePrefix("Bearer "))
        val temporaryUserId =   jwtTokenPayload.claims["userId"]?.asLong() ?: throw RuntimeException("bad token")

        //TODO sprawdznie czy wszystko jest unikalne

        val newUser = userRepository.save(User(
                role = Role.TRAVELER,
                email = confirmGoogleAccount.email,
                googleId = jwtTokenPayload.subject
        ))

        temporaryUserRepository.deleteById(temporaryUserId)

        return createAuthResponse(newUser)
    }


    @Transactional
    fun googleRegister(googleCode: GoogleCode): GoogleAccountInfo {
        val payload = getGoogleAccountInfo(googleCode.code)
        val temporaryUser = registerByGoogle(payload)
        val token = jwtTokenProvider.createTokenForTemporaryUser(temporaryUser.googleId, temporaryUser.id)
        return GoogleAccountInfo(
                email = temporaryUser.email,
                firstName = temporaryUser.firstName,
                lastName = temporaryUser.lastName,
                locale = temporaryUser.country,
                authorizationToken = token
        )
    }


    private fun createAuthResponse(user: User): AuthResponse {
        return AuthResponse(jwtTokenProvider.createToken(user.email, user.id))
    }

    fun findUserByEmail(email:String):User?{
        return userRepository.findUserByEmail(email)
    }

    fun getGoogleAccountInfo(code: String): GoogleIdToken.Payload {
        return GoogleAuthorizationCodeTokenRequest(
                NetHttpTransport(),
                JacksonFactory(),
                "1095850462503-47vu62eqij6r2r8k1ucugo34gdc5dide.apps.googleusercontent.com",
                "ERge9vjfR2Xks0C19Mz2xny9",
                code,
                "http://localhost:9615"
        ).execute().parseIdToken().payload ?: throw RuntimeException("err")
    }


    private fun registerByGoogle(googleTokenResponse: GoogleIdToken.Payload): TemporaryUser {
        return temporaryUserRepository.save(TemporaryUser(
                firstName = googleTokenResponse["given_name"] as String,
                lastName = googleTokenResponse["family_name"] as String,
                googleId = googleTokenResponse.subject,
                country = googleTokenResponse["locale"] as String,
                email = googleTokenResponse.email
        ))
    }

}