package pl.umk.mat.locals.auth

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class LoginRequest(

        @field:Email
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val email: String,

        @field:Size(min = 8, max = 32)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val password: String
)