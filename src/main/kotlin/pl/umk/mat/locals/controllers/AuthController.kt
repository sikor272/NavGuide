package pl.umk.mat.locals.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.AuthResponse
import pl.umk.mat.locals.dto.LoginRequest
import pl.umk.mat.locals.dto.RegisterRequest
import pl.umk.mat.locals.services.UserService
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
        private val userService: UserService
) {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(
            @RequestBody @Valid loginRequest: LoginRequest
    ): AuthResponse {
        return userService.login(loginRequest)
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(
            @RequestBody @Valid registerRequest: RegisterRequest
    ): AuthResponse {
        return userService.register(registerRequest)
    }

}