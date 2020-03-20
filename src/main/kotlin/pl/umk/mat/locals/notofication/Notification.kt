package pl.umk.mat.locals.notofication

import pl.umk.mat.locals.user.User

import java.util.*
import javax.persistence.*


@Entity
data class Notification(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val name: String,

        val description: String,

        val date: Date,

        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,

        @Enumerated(EnumType.STRING)
        val status: NotificationStatus = NotificationStatus.NEW
)