package pl.umk.mat.locals.services

import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.GuestTripResponse
import pl.umk.mat.locals.repositories.TripRepository

@Service

class GuestService(
    private val tripRepository: TripRepository

){
    fun getAllTripsByLocalization(lat: Float, lon: Float): List<GuestTripResponse> {
        return tripRepository.findAllTripsByPoint(lat, lon).asSequence()
                .map {
                    GuestTripResponse(
                            name = it.name
                    )
                }.toList()
    }


}
