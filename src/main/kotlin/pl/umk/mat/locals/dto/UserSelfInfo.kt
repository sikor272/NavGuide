package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Experience
import pl.umk.mat.locals.models.Role
import pl.umk.mat.locals.models.User
import java.util.*

@ApiModel(value = "Self User Info")
data class UserSelfInfo(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lastName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val country: String,

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

        val telephone: String,

        val experience: Experience,

        val avatar: String,

        val interests: List<InterestDto> = emptyList()

) {
    constructor(user: User) : this(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            country = user.lastName,
            role = user.role,
            email = user.email,
            googleId = user.googleId,
            emailConfirmationCode = user.emailConfirmationCode,
            ban = user.ban,
            passwordResetCode = user.passwordResetCode,
            tokenUniqueId = user.tokenUniqueId,
            telephone = user.telephone,
            experience = user.experience,
            avatar = user.avatar,
            interests = user.interests.map {
                InterestDto(it)
            }
    )
}