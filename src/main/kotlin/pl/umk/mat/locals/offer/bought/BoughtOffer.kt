package pl.umk.mat.locals.offer.bought

import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.BaseEntity
import java.util.*
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class BoughtOffer(


        @ManyToOne(fetch = FetchType.LAZY)
        val offer: Offer,

        @ManyToOne(fetch = FetchType.LAZY)
        val traveler: User,

        val date: Date = Date(),

        val plannedDate: Date,

        val price: Float,

        val wasTheGuide: Boolean = false,

        val wasTheTraveler: Boolean = false,

        @ManyToOne(fetch = FetchType.LAZY)
        val guide: GuideProfile
) : BaseEntity()