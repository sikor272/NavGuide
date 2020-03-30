package pl.umk.mat.locals.offer.purchase

import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.user.UserDto
import pl.umk.mat.locals.utils.enumerations.Status
import java.util.*

data class PurchaseRequestDto(
        val id: Long,
        val message: String,
        val offer: OfferDto,
        val plannedDate: Date,
        val traveler: UserDto,
        val status: Status,
        val feedbackMessage: String?
) {
    constructor(purchaseRequest: PurchaseRequest) : this(
            id = purchaseRequest.id,
            message = purchaseRequest.message,
            offer = OfferDto(purchaseRequest.offer),
            plannedDate = purchaseRequest.plannedDate,
            traveler = UserDto(purchaseRequest.traveler),
            status = purchaseRequest.status,
            feedbackMessage = purchaseRequest.feedbackMessage
    )
}