package pl.umk.mat.locals.user.interest

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
@ApiModel
data class NewInterest(
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        @field:Size(min = 1, max = 100)
        @field:NotBlank
        val name: String
)