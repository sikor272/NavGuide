package pl.umk.mat.locals.offer.feedback

data class NewFeedback(
        val offerId: Long,
        val scoreOffer: Int,
        val scoreGuide: Int,
        val comment: String
)