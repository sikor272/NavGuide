package pl.umk.mat.locals.offer.tag

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class NewTag(
        @field:NotBlank
        @field:Size(min = 1, max = 100)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val name: String
)