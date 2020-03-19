package pl.umk.mat.locals.guide

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.offer.bought.BoughtOfferDto


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

    @GetMapping("/{id}/offers")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get Guide offers", authorizations = [Authorization("JWT Token")])
    fun getGuideOffers(
            @PathVariable id: Long
    ): List<OfferDto> {
        return guideService.getAllGuideOffers(id)
    }

    @GetMapping("/{id}/history")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get Guide offers history", authorizations = [Authorization("JWT Token")])
    fun getGuideOffersHistory(
            @PathVariable id: Long
    ): List<BoughtOfferDto> {
        return guideService.getGuideHistoryOffer(id)
    }
}