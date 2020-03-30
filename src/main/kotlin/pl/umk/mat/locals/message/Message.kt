package pl.umk.mat.locals.message

import pl.umk.mat.locals.offer.purchase.PurchaseRequest
import pl.umk.mat.locals.user.User
import java.util.*
import javax.persistence.*


@Entity
data class Message(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val description: String,

        val date: Date,

        @ManyToOne(fetch = FetchType.LAZY)
        val author: User,

        @ManyToOne(fetch = FetchType.LAZY)
        val purchaseRequest: PurchaseRequest
)