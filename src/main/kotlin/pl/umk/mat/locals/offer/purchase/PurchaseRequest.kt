package pl.umk.mat.locals.offer.purchase

import pl.umk.mat.locals.message.Message
import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User

import pl.umk.mat.locals.utils.enumerations.Status
import java.util.*
import javax.persistence.*

@Entity
data class PurchaseRequest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        val offer: Offer,

        @ManyToOne(fetch = FetchType.LAZY)
        val traveler: User,

        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(name = "message_id")
        val message: List<Message> = emptyList(),

        val createdAt: Date = Date(),

        val plannedDate: Date,

        @Enumerated(EnumType.STRING)
        val status: Status = Status.PENDING,

        val feedbackMessage: String? = null


)