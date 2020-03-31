package pl.umk.mat.locals.offer.feedback

import java.util.*
import pl.umk.mat.locals.offer.OfferDto

data class FeedbackDto(
        val scoreOffer: Int,
        val scoreGuide: Int,
        val comment: String,
        val date: Date,
        val offer: OfferDto
) {
    constructor(feedback: Feedback) : this(
            scoreOffer = feedback.scoreOffer,
            scoreGuide = feedback.scoreGuide,
            comment = feedback.comment,
            date = feedback.date,
            offer = OfferDto(feedback.offer)
    )
}