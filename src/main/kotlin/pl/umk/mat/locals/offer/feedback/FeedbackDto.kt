package pl.umk.mat.locals.offer.feedback

data class FeedbackDto(
        val scoreOffer: Int,
        val scoreGuide: Int,
        val comment: Long
) {
    constructor(feedback: Feedback) : this(
            scoreOffer = feedback.scoreOffer,
            scoreGuide = feedback.scoreGuide,
            comment = feedback.comment
    )
}