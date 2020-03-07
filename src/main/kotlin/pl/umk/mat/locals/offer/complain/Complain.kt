package pl.umk.mat.locals.offer.complain

import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User
import javax.persistence.*

@Entity
data class Complain(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val author: User,

        val description: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val target: Offer

)