package pl.umk.mat.locals.dto.In

import org.hibernate.validator.constraints.Length

data class ChangePassword(
        val oldPassword: String?,
        @field:Length(min = 8, max = 32, message = "wrong password")
        val newPassword: String
)