package pl.umk.mat.locals.offer.purchase


import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferRepository
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.enumerations.ChangeStatus
import pl.umk.mat.locals.utils.enumerations.Status
import pl.umk.mat.locals.utils.exceptions.UserAuthException
import pl.umk.mat.locals.utils.findByIdOrThrow
import javax.transaction.Transactional

@Service
class PurchaseRequestService(
        private val purchaseRequestRepository: PurchaseRequestRepository,
        private val offerRepository: OfferRepository,
        private val userRepository: UserRepository
) {

    @Transactional
    fun addPurchaseOffer(newPurchaseRequest: NewPurchaseRequest, user: User) {
        val offer = offerRepository.findByIdOrThrow(newPurchaseRequest.offerId)
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
        //val offers = user.guideProfile?.offers ?: emptyList()
        if( user.guideProfile == null) throw UserAuthException("You are not owner of this resource")
        val offers = offerRepository.findAllByOwner(user.guideProfile)
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
        val purchaseRequest = purchaseRequestRepository.findByIdOrThrow(id)
        if (purchaseRequest.offer.owner.user != user) throw UserAuthException("You are not owner of this resource")
        purchaseRequestRepository.save(
                purchaseRequest.copy(
                        status = when (changePurchaseOfferStatus.status) {
                            ChangeStatus.ACCEPT -> Status.ACCEPTED
                            ChangeStatus.REJECT -> Status.REJECTED
                        },
                        message = changePurchaseOfferStatus.message
                )
        )
    }


}