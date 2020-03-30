package pl.umk.mat.locals.offer.guest

import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferRepository
import java.util.*

@Service
class GuestService(
        private val offerRepository: OfferRepository
) {
    fun getAllOffersByGeoLocalization(lat: Double, lon: Double, radius: Long): List<GuestOfferDto> {
        return offerRepository.findAllOffersByPoint(lat, lon, radius).asSequence().filter {
            it.end >= Date()
        }.map {
            GuestOfferDto(it)
        }.toList()
    }

    fun getAllOffersByCity(city: String): List<GuestOfferDto> {
        return offerRepository.findAllOffersByCity(city).asSequence().filter {
            it.end >= Date()
        }.map {
            GuestOfferDto(it)
        }.toList()
    }

    fun getRandomOffers(): List<GuestOfferDto> {
        return offerRepository.findRandomOffers().asSequence().filter {
            it.end >= Date()
        }.map {
            GuestOfferDto(it)
        }.toList()
    }

}
