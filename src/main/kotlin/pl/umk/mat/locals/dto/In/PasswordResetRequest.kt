package pl.umk.mat.locals.dto.In

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email

@ApiModel(value = "Password reset request")
data class PasswordResetRequest(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        @field:Email
        val email: String
)