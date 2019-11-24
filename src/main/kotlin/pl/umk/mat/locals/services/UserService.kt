package pl.umk.mat.locals.services

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.AuthResponse
import pl.umk.mat.locals.dto.LoginRequest
import pl.umk.mat.locals.dto.RegisterRequest
import pl.umk.mat.locals.exceptions.ResourceAlreadyExistException
import pl.umk.mat.locals.exceptions.UserAuthException
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.repositories.UserRepository
import pl.umk.mat.locals.security.JwtTokenProvider
import java.util.*
import javax.transaction.Transactional

@Service
class UserService(
        private val userRepository: UserRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider
        ) {

    fun findUserByUsername(username: String): User? {
        return userRepository.findUserByUsername(username)
    }

    fun login(loginRequest: LoginRequest): AuthResponse {

        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))
        val user = findUserByUsername(loginRequest.username) ?: throw UserAuthException("User not found")
        return createAuthResponse(user)

    }

    @Transactional
    fun register(registerRequest: RegisterRequest): AuthResponse {

        if (userRepository.findUserByUsername(registerRequest.username) != null) {
            throw ResourceAlreadyExistException("Username already exist.")
        }

        val newUser = userRepository.save(User(
                username = registerRequest.username,
                password = bCryptPasswordEncoder.encode(registerRequest.password),
                role = "guest",
                email = registerRequest.email
        ))

        return createAuthResponse(newUser)
    }

    private fun createAuthResponse(user: User): AuthResponse {
        return AuthResponse(jwtTokenProvider.createToken(user.username, user.id, Date()))
    }
}