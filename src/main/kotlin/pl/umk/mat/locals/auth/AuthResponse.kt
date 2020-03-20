package pl.umk.mat.locals.auth

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.user.Role
import pl.umk.mat.locals.user.interest.InterestDto
import pl.umk.mat.locals.utils.enumerations.Country


@ApiModel(value = "Auth response")
data class AuthResponse(
        val id: Long,
        @field:ApiModelProperty(notes = "This token will be use to authorization.")
        val token: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lastName: String,
        @field:ApiModelProperty(notes = "Country code ISO 3166-1 alpha-2")
        val country: Country,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val role: Role,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val telephone: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val email: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val experience: Int,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val avatar: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val interests: List<InterestDto>,

        val age: Int?
)