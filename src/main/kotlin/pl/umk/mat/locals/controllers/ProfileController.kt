package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.GoogleCode
import pl.umk.mat.locals.dto.GuideRequestDto
import pl.umk.mat.locals.dto.SelfGuideRequest
import pl.umk.mat.locals.dto.UserSelfInfo
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.UserService
import javax.validation.Valid

@RestController
@RequestMapping("/profile")
@Api(tags = ["Profile Controller"], description = "This controller provides logic for authenticated users to manage his account.")
class ProfileController(
        private val userService: UserService
) {

    @ApiOperation(value = "Connect account created using the password to Google account.", authorizations = [Authorization("JWT Token")])
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
    @ApiOperation("Get basic information about yourself.", authorizations = [Authorization("JWT Token")])
    fun whoAmI(
            @AuthenticationPrincipal principal: UserPrincipal
    ): UserSelfInfo {
        return userService.getSelfUserInfo(principal.user)
    }

    @PostMapping("/resend")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Resend email confirmation code.", authorizations = [Authorization("JWT Token")])
    fun resendValidationMail(
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        userService.sendVerificationMail(principal.user)
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Logout from all devices.", authorizations = [Authorization("JWT Token")])
    fun logoutFromAll(
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        userService.logoutFromAll(principal.user)
    }

    @PostMapping("/guide")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Add request to become a guide.", authorizations = [Authorization("JWT Token")])
    fun addRequestForGuide(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid guideRequest: GuideRequestDto
    ) {
        userService.addRequestForGuide(principal.user, guideRequest)
    }

    @GetMapping("/guide")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all own guide requests.", authorizations = [Authorization("JWT Token")])
    fun getAllGuideApplication(
            @AuthenticationPrincipal principal: UserPrincipal
    ): List<SelfGuideRequest> {
        return userService.getAllGuideApplication(principal.user)
    }
}