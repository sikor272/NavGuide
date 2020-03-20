package pl.umk.mat.locals.auth


import pl.umk.mat.locals.utils.enumerations.Country
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class TemporaryUser(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val firstName: String,

        val lastName: String,

        val googleId: String,

        val country: Country,

        val email: String,

        val createdAt: Date = Date()
)