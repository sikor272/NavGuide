package pl.umk.mat.locals.models

import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val username: String,

        val password: String,

        val role: String,

        val email: String

)