package pl.umk.mat.locals.services

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.config.Config
import pl.umk.mat.locals.dto.`in`.*
import pl.umk.mat.locals.dto.out.*
import pl.umk.mat.locals.exceptions.BadRequest
import pl.umk.mat.locals.exceptions.ResourceAlreadyExistException
import pl.umk.mat.locals.exceptions.UserAuthException
import pl.umk.mat.locals.models.GuideRequest
import pl.umk.mat.locals.models.TemporaryUser
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.models.enumerations.Country
import pl.umk.mat.locals.models.enumerations.GuideRequestStatus
import pl.umk.mat.locals.repositories.GuideRequestRepository
import pl.umk.mat.locals.repositories.InterestRepository
import pl.umk.mat.locals.repositories.TemporaryUserRepository
import pl.umk.mat.locals.repositories.UserRepository
import pl.umk.mat.locals.security.JwtTokenProvider
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import javax.security.auth.message.AuthException
import javax.transaction.Transactional


@Service
class UserService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider,
        private val temporaryUserRepository: TemporaryUserRepository,
        private val emailService: EmailService,
        private val interestRepository: InterestRepository,
        private val guideRequestRepository: GuideRequestRepository,
        private val config: Config
) {

    fun localLogin(loginRequest: LoginRequest): AuthResponse {
        val user = userRepository.findUserByEmail(loginRequest.email) ?: throw UserAuthException("User not found.")
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password))
        return createAuthResponse(user)
    }

    fun googleLogin(googleCode: GoogleCode): AuthResponse {
        val googleAccountInfo = getGoogleAccountInfo(googleCode.code, googleCode.request)
        val user = userRepository.findUserByGoogleId(googleAccountInfo.subject)
                ?: throw UserAuthException("User not found.")
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
                country = registerRequest.country,
                telephone = registerRequest.telephone,
                experience = registerRequest.experience,
                interests = interestRepository.findAllById(registerRequest.interests).asSequence().toList(),
                gender = registerRequest.gender,
                age = registerRequest.age
        ))
        sendVerificationMail(newUser)
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
                googleId = jwtTokenPayload.subject,
                telephone = confirmGoogleAccount.telephone,
                experience = confirmGoogleAccount.experience,
                interests = interestRepository.findAllById(confirmGoogleAccount.interests).asSequence().toList(),
                gender = confirmGoogleAccount.gender,
                age = confirmGoogleAccount.age
        ))

        temporaryUserRepository.deleteById(temporaryUserId)
        sendVerificationMail(newUser)
        return createAuthResponse(newUser)
    }


    @Transactional
    fun googleRegister(googleCode: GoogleCode): GoogleAccountInfo {
        val payload = getGoogleAccountInfo(googleCode.code, googleCode.request)

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
        val payload = getGoogleAccountInfo(googleCode.code, googleCode.request)

        if (userRepository.existsUserByGoogleId(payload.subject))
            throw ResourceAlreadyExistException("User with this GoogleId already exist.")

        temporaryUserRepository.deleteTemporaryUsersByGoogleId(payload.subject)

        userRepository.save(user.copy(
                googleId = payload.subject
        ))
    }

    private fun createAuthResponse(user: User): AuthResponse {
        return AuthResponse(
                token = jwtTokenProvider.createToken(user.email, user.id, user.tokenUniqueId),
                firstName = user.firstName,
                lastName = user.lastName,
                country = user.country,
                role = user.role,
                email = user.email,
                telephone = user.telephone,
                experience = user.experience,
                avatar = config.imageServerUrl + user.avatar,
                interests = user.interests.map {
                    InterestDto(it)
                },
                age = user.age
        )
    }

    fun findUserByEmail(email: String): User? {
        return userRepository.findUserByEmail(email)
    }

    private fun getGoogleAccountInfo(code: String, requestUrl: String): GoogleIdToken.Payload {
        return GoogleAuthorizationCodeTokenRequest(
                NetHttpTransport(),
                JacksonFactory(),
                "1095850462503-47vu62eqij6r2r8k1ucugo34gdc5dide.apps.googleusercontent.com",
                "ERge9vjfR2Xks0C19Mz2xny9",
                code,
                requestUrl
        ).execute().parseIdToken().payload ?: throw AuthException("Error receiving data from google.")
    }


    private fun createTemporaryGoogleUserIfNotExist(googleTokenResponse: GoogleIdToken.Payload): TemporaryUser {
        return temporaryUserRepository.findTemporaryUserByGoogleId(googleTokenResponse.subject)
                ?: temporaryUserRepository.save(TemporaryUser(
                        firstName = googleTokenResponse["given_name"] as String,
                        lastName = googleTokenResponse["family_name"] as String,
                        googleId = googleTokenResponse.subject,
                        country = Country.valueOf((googleTokenResponse["locale"] as String).toUpperCase()),
                        email = googleTokenResponse.email
                ))
    }

    fun getSelfUserInfo(user: User): UserSelfInfo {
        return UserSelfInfo(user, config.imageServerHost)
    }

    fun confirmEmail(emailConfirmationCode: EmailConfirmationCode) {
        val user = userRepository.findUserByEmail(emailConfirmationCode.email)
                ?: throw UserAuthException("User with this email doesnt exist.")
        if (user.emailConfirmationCode == emailConfirmationCode.code) {
            userRepository.save(
                    user.copy(
                            emailConfirmationCode = null
                    )
            )
        } else {
            throw UserAuthException("Bad confirmation code.")
        }
    }

    fun resetPasswordRequest(passwordResetRequest: PasswordResetRequest) {
        val user = userRepository.findUserByEmail(passwordResetRequest.email)
                ?: throw UserAuthException("User with this email doesnt exist.")
        userRepository.save(
                user.copy(
                        passwordResetCode = (1..5).map { kotlin.random.Random.nextInt(0, 10) }.map { "1234567890"[it] }.joinToString("")
                )
        )
        emailService.sendPasswordResetRequestMail(user.email, user.passwordResetCode
                ?: throw UserAuthException("Cannot send email."))
    }

    fun confirmResetPassword(confirmPasswordReset: ConfirmPasswordReset) {
        val user = userRepository.findUserByEmail(confirmPasswordReset.email)
                ?: throw UserAuthException("User with this email doesnt exist.")
        val availableLetters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123654789"
        val password = (1..10).map { kotlin.random.Random.nextInt(0, availableLetters.length) }.map { availableLetters[it] }.joinToString("")
        userRepository.save(
                user.copy(
                        password = passwordEncoder.encode(password)
                )
        )
        emailService.sendNewPassword(user.email, password)
    }

    fun logoutFromAll(user: User) {
        userRepository.save(
                user.copy(
                        tokenUniqueId = kotlin.random.Random.nextInt(100000, 1000000000)
                )
        )
    }

    fun sendVerificationMail(user: User) {
        emailService.sendEmailConfirmation(user.email, user.emailConfirmationCode
                ?: throw UserAuthException("Cannot send confirmation email."))
    }

    @Transactional
    fun addRequestForGuide(user: User, guideRequest: GuideRequestDto) {
        if (guideRequestRepository.existsByUserAndStatus(user, GuideRequestStatus.PENDING)) throw ResourceAlreadyExistException("You already have pending request.")
        guideRequestRepository.save(GuideRequest(
                user = user,
                languages = guideRequest.languages,
                experience = guideRequest.experience,
                description = guideRequest.description
        ))
    }

    fun getAllGuideApplication(user: User): List<SelfGuideRequest> {
        return guideRequestRepository.getAllByUser(user).map {
            SelfGuideRequest(it)
        }
    }

    @Transactional
    fun setUserAvatar(file: MultipartFile, user: User) {
        val fileExtension = file.originalFilename?.substringAfterLast(".")?.toLowerCase()
                ?: throw BadRequest("Incorrect file extension.")
        if (fileExtension == file.originalFilename) throw BadRequest("Incorrect file extension.")
        if (!config.imageRegex.toRegex().matches(fileExtension)) throw BadRequest("Incorrect file type (only jpg, jpeg supported).")

        val filename = "avatar_${user.firstName.toLowerCase()}_${user.lastName.toLowerCase()}_${System.currentTimeMillis()}.$fileExtension"

        val filePatch = config.imageDir + filename
        if (File(filePatch).exists()) throw RuntimeException("File exist try later.")

        File(config.imageDir + user.avatar.substringAfterLast("/")).delete()

        userRepository.save(
                user.copy(
                        avatar = config.imageServerUrl + filename
                )
        )

        Files.copy(
                file.inputStream,
                Path.of(filePatch),
                StandardCopyOption.REPLACE_EXISTING
        )
    }

    @Transactional
    fun updateProfile(user: User, newUserData: NewUserData): UserSelfInfo {
        return UserSelfInfo(userRepository.save(
                user.copy(
                        email = newUserData.email,
                        firstName = newUserData.firstName,
                        lastName = newUserData.lastName,
                        country = newUserData.country,
                        telephone = newUserData.telephone,
                        experience = newUserData.experience,
                        interests = interestRepository.findAllById(newUserData.interests).asSequence().toList(),
                        gender = newUserData.gender
                )),
                config.imageServerHost
        )
    }

    @Transactional
    fun changeUserPassword(user: User, changePassword: ChangePassword) {

        if (!(user.password == null && changePassword.oldPassword == null) || !passwordEncoder.matches(changePassword.oldPassword, user.password))
            throw UserAuthException("Wrong password.")

        userRepository.save(
                user.copy(
                        password = passwordEncoder.encode(changePassword.newPassword)
                )
        )
    }

}