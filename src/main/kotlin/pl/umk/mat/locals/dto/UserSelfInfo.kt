package pl.umk.mat.locals.dto

import pl.umk.mat.locals.models.User

data class UserSelfInfo(
        val firstName: String
) {
    constructor(user: User) : this(firstName = user.firstName)
}