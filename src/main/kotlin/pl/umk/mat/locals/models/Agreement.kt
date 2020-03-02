package pl.umk.mat.locals.models

import pl.umk.mat.locals.models.enumerations.Status
import java.util.*
import javax.persistence.*

@Entity
data class Agreement(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val offer: Offer,

        val description: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val traveler: User,

        @Enumerated(EnumType.STRING)
        val status: Status = Status.PENDING,

        val plannedDate: Date,

        val price: Float
)