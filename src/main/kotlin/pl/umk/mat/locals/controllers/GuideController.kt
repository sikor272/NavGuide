package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.out.GuideProfileDto
import pl.umk.mat.locals.dto.out.OfferDto
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.GuideService


@RestController
@RequestMapping("/guides")
@Api(tags = ["Guide Controller"], description = "This controller provide logic to menage your guide profile.")
class GuideController(
        private val guideService: GuideService
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get Guide profile", authorizations = [Authorization("JWT Token")])
    fun getGuideProfile(
            @PathVariable id: Long
    ): GuideProfileDto {
        return guideService.getGuideProfile(id)
    }

    @GetMapping("/offers")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all own offers.", authorizations = [Authorization("JWT Token")])
    fun getAllOwnOffers(
            @AuthenticationPrincipal principal: UserPrincipal
    ): List<OfferDto> {
        return guideService.getGuideOffer(principal.user.id)
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all offers purchase requests.", authorizations = [Authorization("JWT Token")])
    fun getAllOffersBuyRequests(
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        //ToDo
    }


}