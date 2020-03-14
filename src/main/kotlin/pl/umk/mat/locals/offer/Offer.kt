package pl.umk.mat.locals.offer

import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.offer.agreement.Agreement
import pl.umk.mat.locals.offer.bought.BoughtOffer
import pl.umk.mat.locals.offer.feedback.Feedback
import pl.umk.mat.locals.offer.purchase.PurchaseRequest
import pl.umk.mat.locals.offer.tag.Tag
import java.util.*
import javax.persistence.*


@Entity
data class Offer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String,
        val city: String,
        val lat: Double,
        val lon: Double,
        val radius: Long,
        val begin: Date,
        val end: Date,
        val maxPeople: Long,
        val price: Float,
        val description: String,
        val inSearch: Long = 0,


        @Enumerated(EnumType.STRING)
        val priceType: PriceType = PriceType.PER_GROUP,

        @ManyToOne(fetch = FetchType.LAZY)
        val owner: GuideProfile,

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "offer_tags",
                joinColumns = [JoinColumn(name = "offer_id")],
                inverseJoinColumns = [JoinColumn(name = "tag_id")])
        val tags: List<Tag> = emptyList(),

        @ElementCollection
        val photos: List<String> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val purchaseRequests: List<PurchaseRequest> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val agreements: List<Agreement> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val bought: List<BoughtOffer> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(name = "offer_id")
        val feedbackOffers: List<Feedback> = emptyList()
)