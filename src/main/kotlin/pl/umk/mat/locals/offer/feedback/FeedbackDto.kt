package pl.umk.mat.locals.offer.feedback

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*
import pl.umk.mat.locals.offer.OfferDto

@ApiModel
data class FeedbackDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val scoreOffer: Int,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val scoreGuide: Int,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val comment: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val date: Date,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
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