package pl.umk.mat.locals.auth

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotBlank

@ApiModel
data class GoogleCode(
        @field:NotBlank
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val code: String,

        @field:URL
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val request: String
)

