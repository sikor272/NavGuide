package pl.umk.mat.locals.auth.admin

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.validation.constraints.Future

@ApiModel
data class Ban(
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        @field:Future
        val end: Date
)