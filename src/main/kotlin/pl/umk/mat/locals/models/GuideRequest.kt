package pl.umk.mat.locals.models

import java.util.*
import javax.persistence.*

@Entity
data class GuideRequest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val createdAt: Date = Date(),

        val isAccepted: Boolean = false,

        @ElementCollection
        val languages: List<String>,

        val experience: Experience,

        val description: String,

        @OneToOne(fetch = FetchType.LAZY)
        val user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        val acceptedBy: User? = null
)