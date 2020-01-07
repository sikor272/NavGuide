package pl.umk.mat.locals.models

import org.hibernate.annotations.Fetch
import pl.umk.mat.locals.dto.OfferDto
import pl.umk.mat.locals.repositories.OfferRepository
import javax.persistence.*

@Entity
data class GuideProfile(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @Enumerated(EnumType.STRING)
        @ElementCollection
        val languages: List<Language>,
        @Enumerated(EnumType.STRING)
        val experience: Experience,
        @OneToOne
        val user: User,

        @OneToOne
        val guideRequest: GuideRequest,

        @OneToMany(fetch = FetchType.LAZY)
        val offers : List<Offer> = emptyList()
)