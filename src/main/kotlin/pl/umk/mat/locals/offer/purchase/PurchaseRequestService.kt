package pl.umk.mat.locals.offer.purchase


import org.springframework.stereotype.Service
import pl.umk.mat.locals.guide.GuideProfileRepository
import pl.umk.mat.locals.offer.OfferRepository
import pl.umk.mat.locals.offer.bought.BoughtOfferRepository
import pl.umk.mat.locals.offer.feedback.FeedbackRepository
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.enumerations.ChangeStatus
import pl.umk.mat.locals.utils.enumerations.Status
import pl.umk.mat.locals.utils.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.utils.exceptions.UserAuthException
import pl.umk.mat.locals.utils.findByIdOrThrow
import javax.transaction.Transactional

@Service
class PurchaseRequestService(
        private val purchaseRequestRepository: PurchaseRequestRepository,
        private val offerRepository: OfferRepository,
        private val guideProfileRepository: GuideProfileRepository,
        private val boughtOfferRepository: BoughtOfferRepository,
        private val feedbackRepository: FeedbackRepository
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

    fun getPurchaseRequestsGuide(user: User): List<PurchaseRequestDto> {
        val guide = guideProfileRepository.findByGuideRequestUser(user)
                ?: throw ResourceNotFoundException("Cannot find guide profile")
        return purchaseRequestRepository.getAllByOfferOwner(guide).map {
            PurchaseRequestDto(it, feedbackRepository.findAllByOfferOwner(it.offer.owner).map {
                score-> score.scoreGuide
            }.average(),
                    feedbackRepository.findALLByOffer(it.offer).map {
                        score-> score.scoreOffer
                    }.average(),
                    boughtOfferRepository.findAllByOffer(it.offer).count())
        }
    }

    fun getPurchaseRequestsTravelers(user: User): List<PurchaseRequestDto> {
        return purchaseRequestRepository.getAllByTraveler(user).map {
            PurchaseRequestDto(it, feedbackRepository.findAllByOfferOwner(it.offer.owner).map {
                score-> score.scoreGuide
            }.average(),
                    feedbackRepository.findALLByOffer(it.offer).map {
                        score-> score.scoreOffer
                    }.average(),
                    boughtOfferRepository.findAllByOffer(it.offer).count())
        }
    }

    fun changePurchaseOfferStatus(id: Long, user: User, changePurchaseOfferStatus: ChangePurchaseOfferStatus) {
        val purchaseRequest = purchaseRequestRepository.findByIdOrThrow(id)
        if (purchaseRequest.offer.owner.guideRequest.user != user) throw UserAuthException("You are not owner of this resource")
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