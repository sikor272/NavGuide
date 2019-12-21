package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Experience
import pl.umk.mat.locals.models.Role


@ApiModel(value = "Auth response")
data class AuthResponse(
        @field:ApiModelProperty(notes = "This token will be use to authorization.")
        val token: String,

        val firstName: String,

        val lastName: String,

        val country: String,

        val role: Role = Role.TRAVELER,

        val telephone: String,

        val email: String,

        val experience: Experience = Experience.NOVICE,

        val avatar: String,

        val interests: List<InterestDto>
)