package pl.umk.mat.locals.controllers

import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.GoogleCode
import pl.umk.mat.locals.dto.UserSelfInfo
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.UserService
import javax.validation.Valid

@RestController
@RequestMapping("/profile")
class ProfileController(
        private val userService: UserService
) {

    @PostMapping("/connect/google")
    @ResponseStatus(HttpStatus.OK)
    fun connectAccountToGoogle(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid googleCode: GoogleCode
    ) {
        userService.connectGoogleAccount(googleCode, principal.user)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun whoAmI(
            @AuthenticationPrincipal principal: UserPrincipal
    ): UserSelfInfo {
        return userService.getSelfUserInfo(principal.user)
    }

    @PostMapping("/resend")
    @ResponseStatus(HttpStatus.OK)
    fun resentValidationMail(
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        userService.sendVerificationMail(principal.user)
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    fun logoutFromAll(
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        userService.logoutFromAll(principal.user)
    }

}