package pl.umk.mat.locals.services


import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.`in`.ChangePurchaseOfferStatus
import pl.umk.mat.locals.dto.`in`.ChangePurchaseOfferStatusEnum
import pl.umk.mat.locals.dto.`in`.NewPurchaseRequest
import pl.umk.mat.locals.dto.out.PurchaseRequestDto
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.exceptions.UserAuthException
import pl.umk.mat.locals.models.PurchaseRequest
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.models.enumerations.Status
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.PurchaseRequestRepository
import pl.umk.mat.locals.repositories.TemporaryUserRepository
import pl.umk.mat.locals.repositories.UserRepository
import javax.transaction.Transactional

@Service
class PurchaseRequestService(
        private val purchaseRequestRepository: PurchaseRequestRepository,
        private val offerRepository: OfferRepository,
        private val userRepository: UserRepository
) {

    @Transactional
    fun addPurchaseOffer(newPurchaseRequest: NewPurchaseRequest, user: User) {
        val offer = offerRepository.findByIdOrNull(newPurchaseRequest.offerId)
                ?: throw ResourceNotFoundException("Offer not found")
        userRepository.save(user.copy(
                allowViewProfile = user.allowViewProfile.plus(offer.owner.user)
        ))

        purchaseRequestRepository.save(
                PurchaseRequest(
                        message = newPurchaseRequest.message,
                        offer = offer,
                        traveler = user,
                        plannedDate = newPurchaseRequest.plannedDate
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
        val purchaseRequest = purchaseRequestRepository.findByIdOrNull(id)
                ?: throw ResourceNotFoundException("Not found")
        if (purchaseRequest.offer.owner.user != user) throw UserAuthException("You are not owner of this resource")

        if (changePurchaseOfferStatus.status == ChangePurchaseOfferStatusEnum.ACCEPT) {
            purchaseRequestRepository.save(
                    purchaseRequest.copy(
                            status = Status.ACCEPTED
                    )
            )
            //TODO allow to communicate - jak będzie
        } else {
            purchaseRequestRepository.save(
                    purchaseRequest.copy(
                            status = Status.REJECTED
                    )
            )
        }
    }


}