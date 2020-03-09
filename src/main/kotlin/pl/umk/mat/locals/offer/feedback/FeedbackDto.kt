package pl.umk.mat.locals.offer.feedback

import java.util.*

data class FeedbackDto(
        val scoreOffer: Int,
        val scoreGuide: Int,
        val comment: String,
        val date: Date
) {
    constructor(feedback: Feedback) : this(
            scoreOffer = feedback.scoreOffer,
            scoreGuide = feedback.scoreGuide,
            comment = feedback.comment,
            date = feedback.date
    )
}