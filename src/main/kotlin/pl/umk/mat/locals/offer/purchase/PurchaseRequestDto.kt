package pl.umk.mat.locals.offer.purchase

import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.user.UserDto
import java.util.*

data class PurchaseRequestDto(
        val message: String,
        val offer: OfferDto,
        val plannedDate: Date,
        val traveler: UserDto
) {
    constructor(purchaseRequest: PurchaseRequest, averageGuideMark: Double, averageOfferMark: Double, sold: Int) : this(
            message = purchaseRequest.message,
            offer = OfferDto(purchaseRequest.offer, averageGuideMark, averageOfferMark, sold),
            plannedDate = purchaseRequest.plannedDate,
            traveler = UserDto(purchaseRequest.traveler)
    )
}