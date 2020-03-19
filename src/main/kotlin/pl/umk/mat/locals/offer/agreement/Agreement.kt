package pl.umk.mat.locals.offer.agreement

import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.BaseEntity
import pl.umk.mat.locals.utils.enumerations.Status
import java.util.*
import javax.persistence.*

@Entity
data class Agreement(


        @ManyToOne(fetch = FetchType.LAZY)
        val offer: Offer,

        val description: String,

        @ManyToOne(fetch = FetchType.LAZY)
        val traveler: User,

        @Enumerated(EnumType.STRING)
        val status: Status = Status.PENDING,

        val plannedDate: Date,

        val price: Float
) : BaseEntity()