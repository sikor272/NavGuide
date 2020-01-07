package pl.umk.mat.locals.dto.In

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@ApiModel(value = "Confirm password reset")
data class ConfirmPasswordReset(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        @field:Email
        val email: String,

        @field:ApiModelProperty(notes = "Code received on email.")
        @field:NotBlank(message = "Code cannot be blank!")
        val code: String
)