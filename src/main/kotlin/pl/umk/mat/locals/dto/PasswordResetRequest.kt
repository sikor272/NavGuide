package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Password reset request")
data class PasswordResetRequest(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val email: String
)