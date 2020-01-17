package pl.umk.mat.locals.dto.out

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.models.enumerations.Country
import pl.umk.mat.locals.models.enumerations.Experience
import pl.umk.mat.locals.models.enumerations.Gender
import pl.umk.mat.locals.models.enumerations.Role
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
        val emailConfirmationCode: String?,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val ban: Date?,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val passwordResetCode: String?,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val tokenUniqueId: Int,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val telephone: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val experience: Experience,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val avatar: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val interests: List<InterestDto> = emptyList(),

        val age: Int?,

        val gender: Gender


) {
    constructor(user: User, imgServerHost: String) : this(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            country = user.country,
            role = user.role,
            email = user.email,
            googleId = user.googleId,
            emailConfirmationCode = user.emailConfirmationCode,
            ban = user.ban,
            passwordResetCode = user.passwordResetCode,
            tokenUniqueId = user.tokenUniqueId,
            telephone = user.telephone,
            experience = user.experience,
            avatar = imgServerHost + user.avatar,
            interests = user.interests.map {
                InterestDto(it)
            },
            gender = user.gender,
            age = user.age

    )
}