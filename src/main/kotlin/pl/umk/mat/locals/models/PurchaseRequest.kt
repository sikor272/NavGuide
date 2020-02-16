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
        val offer: Offer,

        @ManyToOne(fetch = FetchType.LAZY)
        val traveler: User,

        val message: String,

        val createdAt: Date = Date(),

        val status: Status = Status.PENDING

)