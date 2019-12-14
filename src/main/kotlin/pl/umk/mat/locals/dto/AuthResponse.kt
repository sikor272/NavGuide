package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty


@ApiModel(value = "Auth response")
data class AuthResponse(
        @field:ApiModelProperty(notes = "This token will be use to authorization.")
        val token: String
)