package pl.umk.mat.locals.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.`in`.ChangePurchaseOfferStatus
import pl.umk.mat.locals.dto.`in`.ChangePurchaseOfferStatusEnum
import pl.umk.mat.locals.dto.`in`.NewComplain
import pl.umk.mat.locals.dto.`in`.NewPurchaseRequest
import pl.umk.mat.locals.dto.out.OfferDto
import pl.umk.mat.locals.dto.out.PurchaseRequestDto
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.exceptions.UserAuthException
import pl.umk.mat.locals.models.Complain
import pl.umk.mat.locals.models.PurchaseRequest
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.repositories.ComplainRepository
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.PurchaseRequestRepository
import javax.transaction.Transactional

@Service

class OfferService(
        private val offerRepository: OfferRepository,
        private val complainRepository: ComplainRepository,
        private val purchaseRequestRepository: PurchaseRequestRepository

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

    @Transactional
    fun addNewComplain(complain: NewComplain, user: User) {
        complainRepository.save(
                Complain(
                        target = offerRepository.findByIdOrNull(complain.offerId) ?: throw ResourceNotFoundException("Offer not found."),
                        description = complain.description,
                        author = user
                )
        )
    }

    @Transactional
    fun addPurchaseOffer(newPurchaseRequest: NewPurchaseRequest, user: User) {
        purchaseRequestRepository.save(
                PurchaseRequest(
                        message = newPurchaseRequest.message,
                        offer = offerRepository.findByIdOrNull(newPurchaseRequest.offerId) ?: throw ResourceNotFoundException("Offer not found"),
                        traveler = user
                )
        )
    }

    @Transactional
    fun getPurchaseRequestsGuide(user: User): List<PurchaseRequestDto> {
        val offers = user.guideProfile?.offers ?: emptyList()
        return offers.map {
            it.purchaseRequests
        }.flatten().map {
            PurchaseRequestDto(it)
        }
    }

    fun getPurchaseRequestsTravelers(user: User): List<PurchaseRequestDto> {
        return purchaseRequestRepository.getAllByTraveler(user).map {
            PurchaseRequestDto(it)
        }
    }

    fun changePurchaseOfferStatus(id: Long, user: User, changePurchaseOfferStatus: ChangePurchaseOfferStatus) {
        val purchaseRequest = purchaseRequestRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Not found")
        if (purchaseRequest.offer.owner.user != user) throw UserAuthException("You are not owner of this resource")
        if (changePurchaseOfferStatus.status == ChangePurchaseOfferStatusEnum.ACCEPT) {
            //TODO
        } else {
            //TODO
        }
    }
}
