package pl.umk.mat.locals.models

import pl.umk.mat.locals.models.enumerations.NotificationStatus
import java.util.*
import javax.persistence.*


@Entity
data class Notification(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false, unique = true, length = 32)
        val name: String,

        val description: String,

        val date: Date,

        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,

        @Enumerated(EnumType.STRING)
        val status: NotificationStatus = NotificationStatus.NEW
)