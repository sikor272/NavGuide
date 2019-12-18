package pl.umk.mat.locals.models

import javax.persistence.*


@Entity
data class Photo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val url: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: User
)