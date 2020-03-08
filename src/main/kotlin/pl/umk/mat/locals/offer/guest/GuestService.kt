package pl.umk.mat.locals.offer.guest

import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferRepository

@Service

class GuestService(
        private val offerRepository: OfferRepository
) {
    fun getAllOffersByGeoLocalization(lat: Double, lon: Double, radius: Long): List<GuestOfferDto> {
        return offerRepository.saveAll(offerRepository.findAllOffersByPoint(lat, lon, radius).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            GuestOfferDto(it)
        }.toList()
    }

    fun getAllOffersByCity(city: String): List<GuestOfferDto> {
        return offerRepository.saveAll(offerRepository.findAllOffersByCity(city).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            GuestOfferDto(it)
        }.toList()
    }

    fun getRandomOffers(): List<GuestOfferDto> {
        return offerRepository.saveAll(offerRepository.findRandomOffers().map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            GuestOfferDto(it)
        }.toList()
    }
}
