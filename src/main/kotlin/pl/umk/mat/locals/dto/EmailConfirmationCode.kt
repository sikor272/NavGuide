package pl.umk.mat.locals.dto

data class EmailConfirmationCode(
        val code: String,
        val email: String
)