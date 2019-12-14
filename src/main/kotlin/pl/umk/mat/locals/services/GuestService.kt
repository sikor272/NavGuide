package pl.umk.mat.locals.services

import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.GuestOfferDto
import pl.umk.mat.locals.repositories.OfferRepository

@Service

class GuestService(
    private val offerRepository: OfferRepository

){
    fun getAllOffersByLocalization(lat: Float, lon: Float, radius: Long): List<GuestOfferDto> {
        return offerRepository.findAllOffersByPoint(lat, lon, radius).asSequence()
                .map {
                    GuestOfferDto(it)
                }.toList()
    }


}
