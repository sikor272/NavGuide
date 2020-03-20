package pl.umk.mat.locals.offer.purchase


import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferRepository
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.enumerations.ChangeStatus
import pl.umk.mat.locals.utils.enumerations.Status
import pl.umk.mat.locals.utils.exceptions.UserAuthException
import pl.umk.mat.locals.utils.findByIdOrThrow
import javax.transaction.Transactional

@Service
class PurchaseRequestService(
        private val purchaseRequestRepository: PurchaseRequestRepository,
        private val offerRepository: OfferRepository
) {

    @Transactional
    fun addPurchaseOffer(newPurchaseRequest: NewPurchaseRequest, user: User) {
        val offer = offerRepository.findByIdOrThrow(newPurchaseRequest.offerId)

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

    fun changePurchaseOfferStatus(id: Long, user: User, changePurchaseOfferStatus: ChangePurchaseOfferStatus) {
        val purchaseRequest = purchaseRequestRepository.findByIdOrThrow(id)
        if (purchaseRequest.offer.owner.user.id != user.id) throw UserAuthException("You are not owner of this resource")
        purchaseRequestRepository.save(
                purchaseRequest.copy(
                        status = when (changePurchaseOfferStatus.status) {
                            ChangeStatus.ACCEPT -> Status.ACCEPTED
                            ChangeStatus.REJECT -> Status.REJECTED
                        },
                        feedbackMessage = changePurchaseOfferStatus.message
                )
        )
    }


}