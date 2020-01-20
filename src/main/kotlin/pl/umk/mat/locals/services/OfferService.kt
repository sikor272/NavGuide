package pl.umk.mat.locals.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.`in`.NewComplain
import pl.umk.mat.locals.dto.out.OfferDto
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.models.Complain
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.repositories.ComplainRepository
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.TagRepository
import javax.transaction.Transactional

@Service

class OfferService(
        private val offerRepository: OfferRepository,
        private val tagRepository: TagRepository,
        private val complainRepository: ComplainRepository

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

    fun getOfferById(id: Long) :OfferDto {
        return OfferDto(offerRepository.findByIdOrNull(id)?:throw ResourceNotFoundException("Didn't find"))
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

    @Transactional
    fun addNewComplain(complain: NewComplain, user: User) {
        complainRepository.save(
                Complain(
                        target = offerRepository.findByIdOrNull(complain.offerId)
                                ?: throw ResourceNotFoundException("Offer not found."),
                        description = complain.description,
                        author = user
                )
        )
    }
}
