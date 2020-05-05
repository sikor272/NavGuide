package pl.umk.mat.locals.notofication

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class NewNotification(
        @field:Size(min = 1, max = 200)
        @field:NotBlank
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val name: String,

        @field:Size(min = 1, max = 2048)
        @field:NotBlank
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val description: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val userId: Long
)