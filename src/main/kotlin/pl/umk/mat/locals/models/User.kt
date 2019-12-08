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

        val emailConfirmationCode: String? = (1..5).map { kotlin.random.Random.nextInt(0,10) }.map { "1234567890"[it] }.joinToString(),

        val ban: Date? = null,

        val passwordResetCode:String? = null,

        val tokenUniqueId: Int =  kotlin.random.Random.nextInt(100000,1000000000)
)