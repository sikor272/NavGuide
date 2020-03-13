package pl.umk.mat.locals.offer.bought

import pl.umk.mat.locals.offer.OfferDto
import java.util.*

data class BoughtOfferDto(
        val offer: OfferDto,
        val date: Date
) {
    constructor(boughtOffer: BoughtOffer, averageGuideMark: Double, averageOfferMark: Double, sold: Int) : this(
            offer = OfferDto(boughtOffer.offer, averageGuideMark, averageOfferMark, sold),
            date = boughtOffer.date
    )
}


