package pl.umk.mat.locals.auth.utils

import java.io.Serializable

data class NewUserRabbitDto(
        val email: String,
        val firstName: String,
        val lastName: String
) : Serializable
