package pl.umk.mat.locals.models

import java.util.*
import javax.persistence.*


@Entity
data class TemporaryUser(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val firstName: String,

        val lastName: String,

        val googleId: String,

        val country: String,

        val email: String,

        val createdAt: Date = Date()
)