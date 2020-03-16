package pl.umk.mat.locals.offer.guest

import io.jsonwebtoken.lang.Collections
import io.jsonwebtoken.lang.Collections.size
import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.offer.OfferRepository

@Service

class GuestService(
        private val offerRepository: OfferRepository
) {
    fun getAllOffersByGeoLocalization(lat: Double, lon: Double, radius: Long): List<GuestOfferDto> {

        val offers = offerRepository.findAllOffersByPoint(lat, lon, radius).asSequence().map {
            GuestOfferDto(it)
        }.toList()
        if (size(offers) < 3)
            return offers.plus(offerRepository.findAllOffersNearByPoint(lat, lon).asSequence().map {
                GuestOfferDto(it)
            }.toList()
            ).distinct()
        return offers
    }

    fun getAllOffersByCity(city: String): List<GuestOfferDto> {
        return offerRepository.findAllOffersByCity(city).asSequence().map {
            GuestOfferDto(it)
        }.toList()
    }

    fun getRandomOffers(): List<GuestOfferDto> {
        return offerRepository.findRandomOffers().asSequence().map {
            GuestOfferDto(it)
        }.toList()
    }
}
