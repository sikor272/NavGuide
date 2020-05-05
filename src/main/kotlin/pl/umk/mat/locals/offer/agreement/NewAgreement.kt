package pl.umk.mat.locals.offer.agreement

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.validation.constraints.Future
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class NewAgreement(

        @field:NotBlank
        @field:Size(min = 1, max = 2048)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val description: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val purchaseRequestId: Long,

        @field:Future
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val plannedDate: Date,

        @field:Min(0)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val price: Float
)