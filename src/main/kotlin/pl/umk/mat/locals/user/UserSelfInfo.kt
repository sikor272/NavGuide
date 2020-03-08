package pl.umk.mat.locals.user

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.user.interest.InterestDto
import pl.umk.mat.locals.utils.enumerations.Country
import pl.umk.mat.locals.utils.enumerations.Gender
import java.util.*

@ApiModel(value = "Self User Info")
data class UserSelfInfo(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lastName: String,

        @field:ApiModelProperty(notes = "Country code ISO 3166-1 alpha-2")
        val country: Country,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val role: Role,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val email: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val googleId: String?,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val ban: Date?,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val tokenUniqueId: Int,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val telephone: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val experience: Int,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val avatar: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val interests: List<InterestDto> = emptyList(),

        val age: Int?,

        val gender: Gender


) {
    constructor(user: User) : this(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            country = user.country,
            role = user.role,
            email = user.email,
            googleId = user.googleId,
            ban = user.ban,
            tokenUniqueId = user.tokenUniqueId,
            telephone = user.telephone,
            experience = user.experience,
            avatar = user.avatar,
            interests = user.interests.map {
                InterestDto(it)
            },
            gender = user.gender,
            age = user.age

    )
}