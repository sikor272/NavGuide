package pl.umk.mat.locals.user

import org.hibernate.validator.constraints.UniqueElements
import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.guide.request.GuideRequest
import pl.umk.mat.locals.notofication.Notification
import pl.umk.mat.locals.offer.agreement.Agreement
import pl.umk.mat.locals.offer.bought.BoughtOffer
import pl.umk.mat.locals.offer.feedback.Feedback
import pl.umk.mat.locals.offer.purchase.PurchaseRequest
import pl.umk.mat.locals.user.interest.Interest
import pl.umk.mat.locals.utils.enumerations.Country
import pl.umk.mat.locals.utils.enumerations.Gender
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val firstName: String,

        val lastName: String,

        @Enumerated(EnumType.STRING)
        val country: Country,

        val password: String? = null,

        @Enumerated(EnumType.STRING)
        val role: Role = Role.TRAVELER,

        @Enumerated(EnumType.STRING)
        val gender: Gender,

        val telephone: String,

        @UniqueElements
        @field:Email
        val email: String,

        @UniqueElements
        val googleId: String? = null,

        val ban: Date? = null,

        val age: Int?,

        val tokenUniqueId: Int = kotlin.random.Random.nextInt(100000, 1000000000),

        val experience: Int,

        val avatar: String = "https://235.ip-51-91-9.eu/img/avatar_default.jpg",

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_interests",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "interest_id")]
        )
        val interests: List<Interest> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        val guideRequests: List<GuideRequest> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        val processedGuideRequests: List<GuideRequest> = emptyList(),

        @OneToOne(fetch = FetchType.EAGER)
        val guideProfile: GuideProfile? = null,

        @OneToMany(fetch = FetchType.LAZY)
        val allowViewProfile: List<User> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        val notification: List<Notification> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        val agreements: List<Agreement> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        val purchases: List<PurchaseRequest> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        val boughtOffers: List<BoughtOffer> = emptyList(),

        @OneToMany(fetch = FetchType.LAZY)
        val feedbackOffers: List<Feedback> = emptyList()


)