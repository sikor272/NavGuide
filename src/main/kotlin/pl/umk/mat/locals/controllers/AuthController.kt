package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.`in`.*
import pl.umk.mat.locals.dto.out.AuthResponse
import pl.umk.mat.locals.dto.out.GoogleAccountInfo
import pl.umk.mat.locals.services.UserService
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
@Api(tags = ["Authentication Controller"], description = "This controller provide login/register logic.")
class AuthController(
        private val userService: UserService
) {

    @ApiOperation("Login with password.")
    @PostMapping("/local/login")
    @ResponseStatus(HttpStatus.OK)
    fun localLogin(
            @RequestBody @Valid loginRequest: LoginRequest
    ): AuthResponse {
        return userService.localLogin(loginRequest)
    }

    @ApiOperation("Register with password.")
    @PostMapping("/local/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun localRegister(
            @RequestBody @Valid registerRequest: RegisterRequest
    ): AuthResponse {
        return userService.localRegister(registerRequest)
    }

    @ApiOperation("Register with Google.")
    @PostMapping("/google/register")
    @ResponseStatus(HttpStatus.OK)
    fun googleRegister(
            @RequestBody @Valid googleCode: GoogleCode
    ): GoogleAccountInfo {
        return userService.googleRegister(googleCode)
    }

    @ApiOperation("Confirm Google registration.")
    @PostMapping("/google/register/confirm")
    @ResponseStatus(HttpStatus.CREATED)
    fun googleConfirmRegister(
            @RequestBody @Valid confirmGoogleAccount: ConfirmGoogleAccount,
            @RequestHeader("Authorization") @ApiParam("This token is obtained from `/auth/google/register` endpoint", required = true) token: String
    ): AuthResponse {
        return userService.googleConfirmRegister(confirmGoogleAccount, token)
    }

    @ApiOperation("Login with Google.")
    @PostMapping("/google/login")
    @ResponseStatus(HttpStatus.OK)
    fun googleLogin(
            @RequestBody @Valid googleCode: GoogleCode
    ): AuthResponse {
        return userService.googleLogin(googleCode)
    }

    @ApiOperation(value = "Confirm email address.")
    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun confirmEmailAddress(
            @RequestBody @Valid emailConfirmationCode: EmailConfirmationCode
    ) {
        userService.confirmEmail(emailConfirmationCode)
    }

    @ApiOperation("Send password reset code.")
    @PostMapping("/reset")
    @ResponseStatus(HttpStatus.OK)
    fun resetPasswordRequest(
            @RequestBody @Valid passwordResetRequest: PasswordResetRequest
    ) {
        userService.resetPasswordRequest(passwordResetRequest)
    }

    @ApiOperation("Confirm password reset code.")
    @PostMapping("/reset/confirm")
    @ResponseStatus(HttpStatus.OK)
    fun confirmResetPassword(
            @RequestBody @Valid confirmPasswordReset: ConfirmPasswordReset
    ) {
        userService.confirmResetPassword(confirmPasswordReset)
    }

}