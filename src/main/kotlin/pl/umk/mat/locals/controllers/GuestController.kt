package pl.umk.mat.locals.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.GuestOfferDto
import pl.umk.mat.locals.services.GuestService

@RestController
@RequestMapping("/guest")
class GuestController (
    private val guestService: GuestService
)   {
    @GetMapping("/offer")
    @ResponseStatus(HttpStatus.OK)
    fun getAllOffers(
            @RequestParam lat : Float,
            @RequestParam lon : Float,
            @RequestParam radius : Long
    ): List<GuestOfferDto>{
        return guestService.getAllOffersByLocalization(lat, lon, radius)
    }
}

