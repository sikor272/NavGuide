package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.out.GuestOfferDto
import pl.umk.mat.locals.services.GuestService

@RestController
@RequestMapping("/guests")
@Api(tags = ["Guest Controller"], description = "This controller provide logic for unauthorized users.")
class GuestController(
        private val guestService: GuestService
) {

    @GetMapping("/offers")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get 10 random offers.")
    fun getRandomOffers(): List<GuestOfferDto> {
        return guestService.getRandomOffers()
    }

    @GetMapping("/offers/geo")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all offers by geolocation and radius.")
    fun getAllOffersByGeoLocalization(
            @RequestParam @ApiParam("latitude", example = "1") lat: Double,
            @RequestParam @ApiParam("longitude", example = "1") lon: Double,
            @RequestParam @ApiParam("radius in meter", example = "12") radius: Long
    ): List<GuestOfferDto> {
        return guestService.getAllOffersByGeoLocalization(lat, lon, radius)
    }

    @GetMapping("/offers/city")
    @ApiOperation("Get all offers by city.")
    @ResponseStatus(HttpStatus.OK)
    fun getAllOffersByCity(
            @RequestParam(value = "name") city: String
    ): List<GuestOfferDto> {
        return guestService.getAllOffersByCity(city)
    }

}