package pl.umk.mat.locals.offer.complain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class NewComplain(
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val offerId: Long,

        @field:NotBlank
        @field:Size(min = 1, max = 2048)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val description: String
)