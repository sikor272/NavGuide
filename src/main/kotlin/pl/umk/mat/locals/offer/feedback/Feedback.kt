package pl.umk.mat.locals.offer.feedback

import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User

import java.util.*
import javax.persistence.*

@Entity
data class Feedback(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        val author: User,

        @ManyToOne(fetch = FetchType.LAZY)
        val offer: Offer,

        val scoreOffer: Int,

        val scoreGuide: Int,

        val comment: String,

        val date: Date = Date()
)