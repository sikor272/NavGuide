package pl.umk.mat.locals.dto.out

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.models.enumerations.Country
import pl.umk.mat.locals.models.enumerations.Gender
import pl.umk.mat.locals.models.enumerations.Role

@ApiModel(value = "Self User Info")
data class UserDto(
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