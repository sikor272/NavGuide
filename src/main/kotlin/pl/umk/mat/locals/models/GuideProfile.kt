package pl.umk.mat.locals.models

import pl.umk.mat.locals.models.enumerations.Language
import javax.persistence.*

@Entity
data class GuideProfile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @Enumerated(EnumType.STRING)
        @ElementCollection
        val languages: List<Language>,
        val experience: Int,
        @OneToOne
        val user: User,

        @OneToOne
        val guideRequest: GuideRequest,

        @OneToMany(fetch = FetchType.LAZY)
        val offers: List<Offer> = emptyList()
)