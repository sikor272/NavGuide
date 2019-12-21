package pl.umk.mat.locals.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.GuestOfferDto
import pl.umk.mat.locals.services.GuestService

@RestController
@RequestMapping("/guests")
class GuestController(
        private val guestService: GuestService
) {
    @GetMapping("/offers")
    @ResponseStatus(HttpStatus.OK)
    fun getRandomOffers(

    ): List<GuestOfferDto> {
        return guestService.getRandomOffers()
    }

    @GetMapping("/offers/geo")
    @ResponseStatus(HttpStatus.OK)
    fun getAllOffersByGeoLocalization(
            @RequestParam lat: Float,
            @RequestParam lon: Float,
            @RequestParam radius: Long
    ): List<GuestOfferDto> {
        return guestService.getAllOffersByGeoLocalization(lat, lon, radius)
    }

    @GetMapping("/offers/city")
    @ResponseStatus(HttpStatus.OK)
    fun getAllOffersByCity(
            @RequestParam city: String
    ): List<GuestOfferDto> {
        return guestService.getAllOffersByCity(city)
    }

    @GetMapping("/offers/tags")
    @ResponseStatus(HttpStatus.OK)
    fun getAllOffersByTags(
            @RequestParam(name = "list") tags: List<String>
    ): List<GuestOfferDto> {
        return guestService.getAllOffersByTags(tags)
    }
}

