package pl.umk.mat.locals.models

import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val password: String? = null,

        val role: Role,

        val email: String,

        val googleId:String? = null,

        val confirmedEmail:Boolean = false

)