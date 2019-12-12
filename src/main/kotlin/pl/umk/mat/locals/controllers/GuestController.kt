package pl.umk.mat.locals.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.GuestTripResponse
import pl.umk.mat.locals.services.GuestService

@RestController
@RequestMapping("/guests/")
class GuestController (
    private val guestService: GuestService
)   {
    @GetMapping("/trips")
    @ResponseStatus(HttpStatus.OK)
    fun getAllTrips(
            @RequestParam lat : Float,
            @RequestParam lon : Float
    ): List<GuestTripResponse>{
        return guestService.getAllTripsByLocalization(lat, lon)
    }
}

