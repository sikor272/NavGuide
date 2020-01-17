package pl.umk.mat.locals.models

import org.hibernate.validator.constraints.UniqueElements
import pl.umk.mat.locals.models.enumerations.Country
import pl.umk.mat.locals.models.enumerations.Experience
import pl.umk.mat.locals.models.enumerations.Gender
import pl.umk.mat.locals.models.enumerations.Role
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

        @Enumerated(EnumType.STRING)
        val country: Country,

        val password: String? = null,

        @Enumerated(EnumType.STRING)
        val role: Role = Role.TRAVELER,

        @Enumerated(EnumType.STRING)
        val gender: Gender,

        val telephone: String,

        @UniqueElements
        @field:Email
        val email: String,

        @UniqueElements
        val googleId: String? = null,

        @UniqueElements
        val facebookId: String? = null,

        val emailConfirmationCode: String? = (1..5).map { kotlin.random.Random.nextInt(0, 10) }.map { "1234567890"[it] }.joinToString(""),

        val ban: Date? = null,

        val age: Int?,

        val passwordResetCode: String? = null,

        val tokenUniqueId: Int = kotlin.random.Random.nextInt(100000, 1000000000),

        @Enumerated(EnumType.STRING)
        val experience: Experience = Experience.NOVICE,

        val avatar: String = "/img/avatar_default.jpg",

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_interests",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "interest_id")]
        )
        val interests: List<Interest> = emptyList(),

        @OneToOne
        val guideProfile: GuideProfile? = null
)