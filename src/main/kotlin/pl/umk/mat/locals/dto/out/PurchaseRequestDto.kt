package pl.umk.mat.locals.dto.out

import pl.umk.mat.locals.models.PurchaseRequest

data class PurchaseRequestDto(
        val message: String,
        val offer: OfferDto
) {
    constructor(purchaseRequest: PurchaseRequest) : this(
            message = purchaseRequest.message,
            offer = OfferDto(purchaseRequest.offer)
    )
}