package pl.umk.mat.locals.guide

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

        @OneToOne(fetch = FetchType.EAGER)
        val user: User,

        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(name = "owner_id")
        val offers: List<Offer> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(name = "guide_profile_id")
        val agreements: List<Agreement> = emptyList()

)