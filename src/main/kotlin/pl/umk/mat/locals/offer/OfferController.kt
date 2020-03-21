package pl.umk.mat.locals.offer

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.auth.utils.UserPrincipal
import pl.umk.mat.locals.offer.feedback.FeedbackDto
import javax.validation.Valid


@RestController
@RequestMapping("/offers")
@Api(tags = ["Offer Controller"], description = "This controller is used to manage offers.")
class OfferController(
        private val offerService: OfferService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new offer.", authorizations = [Authorization("JWT Token")])
    fun createNewOffer(
            @RequestParam file: List<MultipartFile>,
            @ModelAttribute @Valid offer: NewOffer,
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        offerService.addNewOffer(file, offer, principal.user)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get Offer by ID ", authorizations = [Authorization("JWT Token")])
    fun getOfferById(
            @PathVariable id: Long
    ): OfferDto {
        return offerService.getOfferById(id)
    }

    @GetMapping("/{id}/feedback")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get Offer feedback by ID ", authorizations = [Authorization("JWT Token")])
    fun getOfferFeedback(
            @PathVariable id: Long
    ): List<FeedbackDto> {
        return offerService.getFeedbackByOfferId(id)
    }

    @GetMapping("/geo")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all offers by geolocation and radius.", authorizations = [Authorization("JWT Token")])
    fun getAllOffersByGeoLocalization(
            @RequestParam @ApiParam("latitude", example = "1") lat: Double,
            @RequestParam @ApiParam("longitude", example = "1") lon: Double,
            @RequestParam @ApiParam("radius in meter", example = "12") radius: Long
    ): List<OfferDto> {
        return offerService.getAllOffersByGeoLocalization(lat, lon, radius)
    }

    @GetMapping("/city")
    @ApiOperation("Get all offers by city.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun getAllOffersByCity(
            @RequestParam(value = "name") city: String
    ): List<OfferDto> {
        return offerService.getAllOffersByCity(city)
    }

    @GetMapping("/name")
    @ApiOperation("Get all offers by name.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun getAllOffersByName(
            @RequestParam("name") name: String
    ): List<OfferDto> {
        return offerService.getAllOffersByName(name)
    }

    @GetMapping("/near")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get nearest offers ", authorizations = [Authorization("JWT Token")])
    fun getNearestOffers(
            @RequestParam @ApiParam("latitude", example = "1") lat: Double,
            @RequestParam @ApiParam("longitude", example = "1") lon: Double,
            @RequestParam @ApiParam("count", example = "3", defaultValue = "3") count: Int
    ): List<OfferDto> {
        return offerService.getNearestOffers(lat, lon, count)
    }
}