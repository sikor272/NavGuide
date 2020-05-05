package pl.umk.mat.locals.offer.feedback

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class NewFeedback(
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val offerId: Long,

        @field:Min(1)
        @field:Max(5)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val scoreOffer: Int,

        @field:Min(1)
        @field:Max(5)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val scoreGuide: Int,

        @field:NotBlank
        @field:Size(min = 1, max = 2048)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val comment: String
)