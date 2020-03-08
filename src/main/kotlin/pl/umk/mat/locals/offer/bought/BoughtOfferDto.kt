package pl.umk.mat.locals.offer.bought

import pl.umk.mat.locals.offer.OfferDto
import java.util.*

data class BoughtOfferDto(
        val offer: OfferDto,
        val date: Date
) {
    constructor(boughtOffer: BoughtOffer) : this(
            offer = OfferDto(boughtOffer.offer),
            date = boughtOffer.date
    )
}


