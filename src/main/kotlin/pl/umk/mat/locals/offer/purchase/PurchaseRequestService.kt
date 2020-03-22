package pl.umk.mat.locals.offer.purchase


import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import pl.umk.mat.locals.config.Config
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
        private val offerRepository: OfferRepository,
        private val rabbitTemplate: RabbitTemplate,
        private val config: Config
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
        rabbitTemplate.convertAndSend(
                config.rabbitExchangeName, "newPurchaseRequest", NewPurchaseRequestRabbitDto(
                email = offer.owner.user.email,
                firstName = offer.owner.user.firstName,
                lastName = offer.owner.user.lastName,
                offerName = offer.name,
                oneSignalId = offer.owner.user.oneSignalId
        )){
            it.messageProperties.headers["email"] = true
            it.messageProperties.headers["push"] = true
            it
        }
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
        val status = when (changePurchaseOfferStatus.status) {
            ChangeStatus.ACCEPT -> Status.ACCEPTED
            ChangeStatus.REJECT -> Status.REJECTED
        }
        purchaseRequestRepository.save(
                purchaseRequest.copy(
                        status = status,
                        feedbackMessage = changePurchaseOfferStatus.message
                )
        )


        rabbitTemplate.convertAndSend(
                config.rabbitExchangeName, "purchaseRequestStatusChanged", PurchaseRequestStatusChangedRabbitDto(
                email = purchaseRequest.traveler.email,
                firstName = purchaseRequest.traveler.firstName,
                lastName = purchaseRequest.traveler.lastName,
                offerName = purchaseRequest.offer.name,
                guide = PurchaseRequestStatusChangedRabbitDto.Guide(
                        firstName = user.firstName,
                        lastName = user.lastName
                ),
                status = status,
                oneSignalId = purchaseRequest.traveler.oneSignalId
        )){
            it.messageProperties.headers["email"] = true
            it.messageProperties.headers["push"] = true
            it
        }
    }

}