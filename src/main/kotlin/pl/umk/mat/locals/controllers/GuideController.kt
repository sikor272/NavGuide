package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.dto.GuideProfileDto
import pl.umk.mat.locals.dto.NewOffer
import pl.umk.mat.locals.dto.OfferDto
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.GuideService


@RestController
@RequestMapping("/guide")
@Api(tags = ["Guide Controller"], description = "This controller provide logic to menage your guide profile.")
class GuideController(
        private val guideService: GuideService
) {

    @PostMapping("/offers")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new offer.", authorizations = [Authorization("JWT Token")])
    fun createNewOffer(
            @RequestParam file: List<MultipartFile>,
            @ModelAttribute offer: NewOffer,
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        guideService.addNewOffer(file, offer, principal.user)
    }

    @GetMapping("/offers")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all own offers.", authorizations = [Authorization("JWT Token")])
    fun getAllOwnOffers(
            @AuthenticationPrincipal principal: UserPrincipal
    ) {

    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all offers purchase requests.", authorizations = [Authorization("JWT Token")])
    fun getAllOffersBuyRequests(
            @AuthenticationPrincipal principal: UserPrincipal
    ) {

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get Guide profile", authorizations = [Authorization("JWT Token")])
    fun getGuideProfile(
            @PathVariable id: Long
    ): GuideProfileDto{
        return guideService.getGuideProfile(id)

    }

    @GetMapping("/{id}/offers")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get Guide offers", authorizations = [Authorization("JWT Token")])
    fun getGuideOffers(
        @PathVariable id: Long
    ) :List<OfferDto>{
        return guideService.getGuideOffer(id)
    }
}