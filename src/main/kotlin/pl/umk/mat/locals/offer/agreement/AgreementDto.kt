package pl.umk.mat.locals.offer.agreement

import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.offer.purchase.PurchaseRequestDto
import pl.umk.mat.locals.user.UserDto
import pl.umk.mat.locals.utils.enumerations.Status
import java.util.*

data class AgreementDto(
        val id: Long,
        val description: String,
        val offer: OfferDto,
        val traveler: UserDto,
        val status: Status,
        val plannedDate: Date,
        val price: Float,
        val purchase: PurchaseRequestDto
) {
    constructor(agreement: Agreement) : this(
            id = agreement.id,
            description = agreement.description,
            offer = OfferDto(agreement.offer),
            traveler = UserDto(agreement.traveler),
            status = agreement.status,
            plannedDate = agreement.plannedDate,
            price = agreement.price,
            purchase =  PurchaseRequestDto(agreement.purchaseRequest)
    )
}