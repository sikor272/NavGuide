package pl.umk.mat.locals.user

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class OneSignalId(

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = false)
        val oneSignalId: String?
)