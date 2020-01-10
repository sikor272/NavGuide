package pl.umk.mat.locals.models

import pl.umk.mat.locals.models.enumerations.*
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

        @Enumerated(EnumType.STRING)
        val experience: Experience,

        val description: String,

        val message: String? = null,

        @Enumerated(EnumType.STRING)
        val status: GuideRequestStatus = GuideRequestStatus.PENDING,

        @OneToOne(fetch = FetchType.LAZY)
        val user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        val processedBy: User? = null
)