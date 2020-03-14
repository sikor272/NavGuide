package pl.umk.mat.locals.guide.request

import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.enumerations.Language
import pl.umk.mat.locals.utils.enumerations.Status
import java.util.*
import javax.persistence.*

@Entity
data class GuideRequest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val createdAt: Date = Date(),

        @ElementCollection
        @Enumerated(EnumType.STRING)
        val languages: List<Language>,

        val experience: Int,

        val description: String,

        val message: String? = null,

        @Enumerated(EnumType.STRING)
        val status: Status = Status.PENDING,

        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        val processedBy: User? = null

)