package pl.umk.mat.locals.dto.out

import pl.umk.mat.locals.models.PurchaseRequest
import java.util.*

data class PurchaseRequestDto(
        val message: String,
        val offer: OfferDto,
        val plannedDate: Date,
        val traveler: UserDto
) {
    constructor(purchaseRequest: PurchaseRequest) : this(
            message = purchaseRequest.message,
            offer = OfferDto(purchaseRequest.offer),
            plannedDate = purchaseRequest.plannedDate,
            traveler = UserDto(purchaseRequest.traveler)
    )
}