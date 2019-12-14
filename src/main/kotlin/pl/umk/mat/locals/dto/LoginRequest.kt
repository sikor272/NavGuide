package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Login with password request")
data class LoginRequest(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val email: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val password: String
)