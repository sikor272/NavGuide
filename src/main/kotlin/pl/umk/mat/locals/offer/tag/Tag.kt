package pl.umk.mat.locals.offer.tag

import pl.umk.mat.locals.offer.Offer
import javax.persistence.*


@Entity
data class Tag(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false, unique = true, length = 32)
        val name: String
)