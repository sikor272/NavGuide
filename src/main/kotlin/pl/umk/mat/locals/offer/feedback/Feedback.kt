package pl.umk.mat.locals.offer.feedback

import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.BaseEntity
import java.util.*
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class Feedback(


        @ManyToOne(fetch = FetchType.LAZY)
        val author: User,

        @ManyToOne(fetch = FetchType.LAZY)
        val offer: Offer,

        val scoreOffer: Int,

        val scoreGuide: Int,

        val comment: String,

        val date: Date = Date()
) : BaseEntity()