package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.dto.`in`.ChangePassword
import pl.umk.mat.locals.dto.`in`.GoogleCode
import pl.umk.mat.locals.dto.`in`.GuideRequestDto
import pl.umk.mat.locals.dto.`in`.NewUserData
import pl.umk.mat.locals.dto.out.SelfGuideRequest
import pl.umk.mat.locals.dto.out.UserSelfInfo
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

    @PutMapping
    @ApiOperation("Change user data.", authorizations = [Authorization("JWT Token")])
    fun updateProfile(
            @RequestBody @Valid newUserData: NewUserData,
            @AuthenticationPrincipal principal: UserPrincipal
    ): UserSelfInfo {
        return userService.updateProfile(principal.user, newUserData)
    }

    @PatchMapping("/password")
    @ApiOperation("Change user password.", authorizations = [Authorization("JWT Token")])
    fun changeUserPassword(
            @RequestBody @Valid changePassword: ChangePassword,
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        userService.changeUserPassword(principal.user, changePassword)
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

    //ToDo
    @GetMapping("/guide")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all own guide requests.", authorizations = [Authorization("JWT Token")])
    fun getAllGuideApplication(
            @AuthenticationPrincipal principal: UserPrincipal
    ): List<SelfGuideRequest> {
        return userService.getAllGuideApplication(principal.user)
    }

    @PostMapping("/avatar")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Set user avatar.", authorizations = [Authorization("JWT Token")])
    fun setUserAvatar(
            @RequestParam file: MultipartFile,
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        userService.setUserAvatar(file, principal.user)
    }
}