package pl.umk.mat.locals.dto.In

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email

@ApiModel(value = "Login with password request")
data class LoginRequest(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        @field:Email
        val email: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        @field:Length(min = 8, max = 32, message = "wrong password")
        val password: String
)