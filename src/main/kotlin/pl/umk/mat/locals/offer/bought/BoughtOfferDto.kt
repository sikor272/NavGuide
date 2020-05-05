package pl.umk.mat.locals.offer.bought

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.user.UserDto
import java.util.*

@ApiModel
data class BoughtOfferDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val offer: OfferDto,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val dateCreate: Date,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val dateMeeting: Date,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val traveler: UserDto
) {
    constructor(boughtOffer: BoughtOffer) : this(
            offer = OfferDto(boughtOffer.offer),
            dateCreate = boughtOffer.date,
            traveler = UserDto(boughtOffer.traveler),
            dateMeeting = boughtOffer.plannedDate
    )
}


