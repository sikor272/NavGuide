package pl.umk.mat.locals.offer.guest

import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferRepository

@Service

class GuestService(
        private val offerRepository: OfferRepository
) {
    fun getAllOffersByGeoLocalization(lat: Double, lon: Double, radius: Long): List<GuestOfferDto> {
        return offerRepository.findAllOffersByPoint(lat, lon, radius).asSequence().map {
            GuestOfferDto(it)
        }.toList()
    }

    fun getAllOffersByCity(city: String): List<GuestOfferDto> {
        return offerRepository.findAllOffersByCity(city).map {
            GuestOfferDto(it)
        }.toList()
    }

    fun getRandomOffers(): List<GuestOfferDto> {
        return offerRepository.findRandomOffers().map {
            GuestOfferDto(it)
        }.toList()
    }
}
