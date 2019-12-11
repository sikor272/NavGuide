package pl.umk.mat.locals.dto

data class GoogleAccountInfo(
        val email: String,
        val firstName: String,
        val lastName: String,
        val country: String,
        val authorizationToken: String
)