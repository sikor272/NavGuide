package pl.umk.mat.locals.offer.feedback

import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User
import javax.persistence.*

@Entity
data class Feedback(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val author: User,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val target: Offer,

        val scoreOffer: Int,

        val scoreGuide: Int,

        val comment: Long
)