package pl.umk.mat.locals.dto

data class ConfirmPasswordReset(
        val email: String,
        val code: String
)