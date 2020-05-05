package pl.umk.mat.locals.auth

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.utils.enumerations.Country
import pl.umk.mat.locals.utils.enumerations.Gender
import pl.umk.mat.locals.utils.validators.annotations.Telephone
import javax.validation.constraints.*

@ApiModel
data class RegisterRequest(

        @field:Size(min = 8, max = 32)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val password: String,

        @field:Email
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val email: String,

        @field:NotBlank
        @field:Size(min = 1, max = 200)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val firstName: String,

        @field:NotBlank
        @field:Size(min = 1, max = 200)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val lastName: String,

        @field:ApiModelProperty(notes = "Country code ISO 3166-1 alpha-2", required = true)
        val country: Country,

        @field:Telephone
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val telephone: String,

        @field:Min(1)
        @field:Max(5)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val experience: Int,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val interests: List<Long>,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val gender: Gender,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = false)
        val age: Int?
)