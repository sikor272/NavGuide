package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Experience
import pl.umk.mat.locals.models.Role


@ApiModel(value = "Auth response")
data class AuthResponse(
        @field:ApiModelProperty(notes = "This token will be use to authorization.")
        val token: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lastName: String,
        @field:ApiModelProperty(notes = "Country code ISO 3166-1 alpha-2")

        val country: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val role: Role = Role.TRAVELER,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val telephone: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val email: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val experience: Experience = Experience.NOVICE,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val avatar: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val interests: List<InterestDto>
)