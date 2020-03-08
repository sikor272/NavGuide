package pl.umk.mat.locals.offer.bought

import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User
import java.util.*
import javax.persistence.*

@Entity
data class BoughtOffer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val offer: Offer,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val traveler: User,

        val date: Date = Date(),

        val plannedDate: Date,

        val price: Float,

        val wasTheGuide: Boolean = false,

        val wasTheTraveler: Boolean = false,

        @ManyToOne(fetch = FetchType.LAZY)
        val guide: GuideProfile
)