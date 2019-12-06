package pl.umk.mat.locals.models

import org.hibernate.validator.constraints.UniqueElements
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val firstName: String,

        val lastName: String,

        val country: String,

        val password: String? = null,

        val role: Role = Role.TRAVELER,

        @UniqueElements
        @field:Email
        val email: String,

        @UniqueElements
        val googleId: String? = null,

        @UniqueElements
        val facebookId: String? = null,

        val confirmedEmail: Boolean = false,

        val ban: Date? = null
)