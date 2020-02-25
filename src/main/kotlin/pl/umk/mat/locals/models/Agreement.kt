package pl.umk.mat.locals.models

import pl.umk.mat.locals.models.enumerations.Status
import javax.persistence.*

@Entity
data class Agreement(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "guide_profile_id")
        val author: GuideProfile,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val offer: Offer,

        val description: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val target: User,

        @Enumerated(EnumType.STRING)
        val status: Status = Status.PENDING

)