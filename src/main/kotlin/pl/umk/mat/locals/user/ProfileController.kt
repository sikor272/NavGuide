package pl.umk.mat.locals.user

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.auth.NewUserData
import pl.umk.mat.locals.auth.utils.UserPrincipal
import pl.umk.mat.locals.guide.request.SelfGuideRequest
import pl.umk.mat.locals.offer.bought.BoughtOfferDto
import pl.umk.mat.locals.offer.purchase.PurchaseRequestDto
import javax.validation.Valid


@RestController
@RequestMapping("/profile")
@Api(tags = ["Profile Controller"], description = "This controller provides logic for authenticated users to manage his account.")
class ProfileController(
        private val userService: UserService
) {

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

    @PutMapping("/push")
    @ApiOperation("Change OneSignal id.", authorizations = [Authorization("JWT Token")])
    fun setOneSignalId(
            @RequestBody @Valid oneSignalId: OneSignalId,
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        return userService.setOneSignalId(principal.user, oneSignalId)
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Logout from all devices.", authorizations = [Authorization("JWT Token")])
    fun logoutFromAll(
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        userService.logoutFromAll(principal.user)
    }

    @GetMapping("/guideRequests")
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

    @GetMapping("/approaches")
    @ApiOperation("Get self purchase offers.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun getPurchaseOffersTravelers(
            @AuthenticationPrincipal principal: UserPrincipal
    ): List<PurchaseRequestDto> {
        return userService.getSelfPurchaseRequests(principal.user)
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get self history offers.", authorizations = [Authorization("JWT Token")])
    fun getOffersHistoryByUserId(
            @AuthenticationPrincipal principal: UserPrincipal
    ): List<BoughtOfferDto> {
        return userService.getSelfHistoryOffer(principal.user)
    }
}