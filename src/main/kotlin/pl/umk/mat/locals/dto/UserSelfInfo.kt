package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.User

@ApiModel(value = "Self User Info")
data class UserSelfInfo(

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String
) {
    constructor(user: User) : this(firstName = user.firstName)
}