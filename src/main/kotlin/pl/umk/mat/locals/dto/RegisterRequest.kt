package pl.umk.mat.locals.dto

data class RegisterRequest(
        val username: String,

        val password: String,

        val email: String
)