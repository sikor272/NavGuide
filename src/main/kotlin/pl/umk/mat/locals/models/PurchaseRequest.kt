package pl.umk.mat.locals.models

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

        val createdAt: Date = Date()

)