package pl.umk.mat.locals.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.*
import pl.umk.mat.locals.services.UserService
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
        private val userService: UserService
) {

    @PostMapping("/local/login")
    @ResponseStatus(HttpStatus.OK)
    fun localLogin(
            @RequestBody @Valid loginRequest: LoginRequest
    ): AuthResponse {
        return userService.localLogin(loginRequest)
    }

    @PostMapping("/local/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun localRegister(
            @RequestBody @Valid registerRequest: RegisterRequest
    ): AuthResponse {
        return userService.localRegister(registerRequest)
    }

    @PostMapping("/google/register")
    @ResponseStatus(HttpStatus.OK)
    fun googleRegister(
            @RequestBody @Valid googleCode: GoogleCode
    ): GoogleAccountInfo {
        return userService.googleRegister(googleCode)
    }

    @PostMapping("/google/register/confirm")
    @ResponseStatus(HttpStatus.CREATED)
    fun googleConfirmRegister(
            @RequestBody @Valid confirmGoogleAccount: ConfirmGoogleAccount,
            @RequestHeader("Authorization") token: String
    ): AuthResponse {
        return userService.googleConfirmRegister(confirmGoogleAccount, token)
    }

    @PostMapping("/google/login")
    @ResponseStatus(HttpStatus.OK)
    fun googleLogin(
            @RequestBody @Valid googleCode: GoogleCode
    ): AuthResponse {
        return userService.googleLogin(googleCode)
    }
}