package pl.umk.mat.locals.auth

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
@Api(tags = ["Authentication Controller"], description = "This controller provide login/register logic.")
class AuthController(
        private val authService: AuthService
) {

    @ApiOperation("Login with password.")
    @PostMapping("/local/login")
    @ResponseStatus(HttpStatus.OK)
    fun localLogin(
            @RequestBody @Valid loginRequest: LoginRequest
    ): AuthResponse {
        return authService.localLogin(loginRequest)
    }

    @ApiOperation("Register with password.")
    @PostMapping("/local/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun localRegister(
            @RequestBody @Valid registerRequest: RegisterRequest
    ): AuthResponse {
        return authService.localRegister(registerRequest)
    }

    @ApiOperation("Register with Google.")
    @PostMapping("/google/register")
    @ResponseStatus(HttpStatus.OK)
    fun googleRegister(
            @RequestBody @Valid googleCode: GoogleCode
    ): GoogleAccountInfo {
        return authService.googleRegister(googleCode)
    }

    @ApiOperation("Confirm Google registration.")
    @PostMapping("/google/register/confirm")
    @ResponseStatus(HttpStatus.CREATED)
    fun googleConfirmRegister(
            @RequestBody @Valid confirmGoogleAccount: ConfirmGoogleAccount,
            @RequestHeader("Authorization") @ApiParam("This token is obtained from `/auth/google/register` endpoint", required = true) token: String
    ): AuthResponse {
        return authService.googleConfirmRegister(confirmGoogleAccount, token)
    }

    @ApiOperation("Login with Google.")
    @PostMapping("/google/login")
    @ResponseStatus(HttpStatus.OK)
    fun googleLogin(
            @RequestBody @Valid googleCode: GoogleCode
    ): AuthResponse {
        return authService.googleLogin(googleCode)
    }

}