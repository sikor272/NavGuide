package pl.umk.mat.locals.offer.purchase

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.user.UserDto
import pl.umk.mat.locals.utils.enumerations.Status
import java.util.*

@ApiModel
data class PurchaseRequestDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val message: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val offer: OfferDto,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val plannedDate: Date,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val traveler: UserDto,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val status: Status,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
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