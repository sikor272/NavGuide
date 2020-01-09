package pl.umk.mat.locals.services

import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.out.OfferDto
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.TagRepository

@Service

class OfferService(
        private val offerRepository: OfferRepository,
        private val tagRepository: TagRepository

) {
    fun getAllOffersByGeoLocalization(lat: Double, lon: Double, radius: Long): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findAllOffersByPoint(lat, lon, radius).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }

    fun getAllOffersByCity(city: String): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findAllOffersByCity(city).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }

    fun getRandomOffers(): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findRandomOffers().map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }
/*
    fun getAllOffersByTags(list: List<String>): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findAllByTagsIn(
                tagRepository.findAllByNameIn(list).asSequence().toList()
        ).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }
*/
}
