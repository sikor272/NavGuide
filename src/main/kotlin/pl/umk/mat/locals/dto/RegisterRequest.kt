package pl.umk.mat.locals.dto

data class RegisterRequest(

        val password: String,

        val email: String,

        val firstName: String,

        val lastName: String,

        val country: String
)