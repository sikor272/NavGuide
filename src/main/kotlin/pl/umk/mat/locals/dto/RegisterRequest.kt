package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Country
import pl.umk.mat.locals.models.Experience
import javax.validation.constraints.Email

@ApiModel(value = "Register with password request")
data class RegisterRequest(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val password: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        @field:Email
        val email: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lastName: String,
        @field:ApiModelProperty(notes = "Country code ISO 3166-1 alpha-2")
        val country: Country,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val telephone: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val experience: Experience,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val interests: List<Long>

)