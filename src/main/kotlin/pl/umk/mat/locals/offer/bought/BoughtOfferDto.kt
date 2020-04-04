package pl.umk.mat.locals.offer.bought

import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.user.UserDto
import java.util.*

data class BoughtOfferDto(
        val offer: OfferDto,
        val dateCreate: Date,
        val dateMeeting: Date,
        val traveler: UserDto
) {
    constructor(boughtOffer: BoughtOffer) : this(
            offer = OfferDto(boughtOffer.offer),
            dateCreate = boughtOffer.date,
            traveler = UserDto(boughtOffer.traveler),
            dateMeeting = boughtOffer.plannedDate
    )
}


