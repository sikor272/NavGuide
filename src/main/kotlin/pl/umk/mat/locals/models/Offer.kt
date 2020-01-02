package pl.umk.mat.locals.models

import java.util.*
import javax.persistence.*


@Entity
data class Offer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String,
        val city: String,
        val lat: Float,
        val lon: Float,
        val radius: Float,
        val begin: Date,
        val end: Date,
        val maxPeople: Long,
        val price: Float,
        @Enumerated(EnumType.STRING)
        val priceType: PriceType = PriceType.PER_GROUP,
        val inSearch: Long = 0,
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "offer_tags",
                joinColumns = [JoinColumn(name = "offer_id")],
                inverseJoinColumns = [JoinColumn(name = "tag_id")])
        val tags: List<Tag> = emptyList()
)