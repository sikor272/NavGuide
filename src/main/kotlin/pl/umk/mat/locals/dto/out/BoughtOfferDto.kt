package pl.umk.mat.locals.dto.out

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.BoughtOffer
import pl.umk.mat.locals.models.Offer
import pl.umk.mat.locals.models.Tag
import pl.umk.mat.locals.models.User
import java.util.*
import javax.persistence.*

data class BoughtOfferDto(
        val id: Long,

        val offer: OfferDto,

        val traveler: UserDto,

        val plannedDate: Date,

        val price: Float,

        val wasTheGuide: Boolean,

        val wasTheTraveler: Boolean
) {
    constructor(boughtOffer: BoughtOffer) : this(
            id = boughtOffer.id,
            offer = OfferDto(boughtOffer.offer),
            traveler = UserDto(boughtOffer.traveler),
            plannedDate = boughtOffer.plannedDate,
            price = boughtOffer.price,
            wasTheGuide = boughtOffer.wasTheGuide,
            wasTheTraveler = boughtOffer.wasTheTraveler
    )
}


