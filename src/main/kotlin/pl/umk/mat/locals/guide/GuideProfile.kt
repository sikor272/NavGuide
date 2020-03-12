package pl.umk.mat.locals.guide

import pl.umk.mat.locals.guide.request.GuideRequest
import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.offer.agreement.Agreement
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.enumerations.Language
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

        @OneToMany(fetch = FetchType.EAGER)
        val offers: List<Offer> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        val agreements: List<Agreement> = emptyList()

)