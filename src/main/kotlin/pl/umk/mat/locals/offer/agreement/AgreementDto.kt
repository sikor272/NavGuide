package pl.umk.mat.locals.offer.agreement

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.offer.purchase.PurchaseRequestDto
import pl.umk.mat.locals.user.UserDto
import pl.umk.mat.locals.utils.enumerations.Status
import java.util.*

@ApiModel
data class AgreementDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val description: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val offer: OfferDto,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val traveler: UserDto,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val status: Status,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val plannedDate: Date,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val price: Float,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
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