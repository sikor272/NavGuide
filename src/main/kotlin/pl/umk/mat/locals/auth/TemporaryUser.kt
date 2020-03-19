package pl.umk.mat.locals.auth

import pl.umk.mat.locals.utils.BaseEntity
import pl.umk.mat.locals.utils.enumerations.Country
import java.util.*
import javax.persistence.Entity


@Entity
data class TemporaryUser(
        val firstName: String,

        val lastName: String,

        val googleId: String,

        val country: Country,

        val email: String,

        val createdAt: Date = Date()
) : BaseEntity()