package pl.umk.mat.locals.models

import pl.umk.mat.locals.models.enumerations.Status
import java.util.*
import javax.persistence.*

@Entity
data class PurchaseRequest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val offer: Offer,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val traveler: User,

        val message: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_guide_id")
        val userGuide: User,

        val createdAt: Date = Date(),

        val status: Status = Status.PENDING

)