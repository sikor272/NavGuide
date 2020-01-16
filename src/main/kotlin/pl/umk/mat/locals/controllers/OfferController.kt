package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.dto.`in`.NewOffer
import pl.umk.mat.locals.dto.out.OfferDto
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.GuideService
import pl.umk.mat.locals.services.OfferService
import javax.validation.Valid


@RestController
@RequestMapping("/offers")
@Api(tags = ["Offer Controller"], description = "This controller is used to manage offers.")
class OfferController(
        private val guideService: GuideService,
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
        guideService.addNewOffer(file, offer, principal.user)
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get 10 random offers.", authorizations = [Authorization("JWT Token")])
    fun getRandomOffers(): List<OfferDto> {
        return offerService.getRandomOffers()
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
}