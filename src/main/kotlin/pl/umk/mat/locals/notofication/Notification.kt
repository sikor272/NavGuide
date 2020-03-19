package pl.umk.mat.locals.notofication

import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.BaseEntity
import java.util.*
import javax.persistence.*


@Entity
data class Notification(


        val name: String,

        val description: String,

        val date: Date,

        @ManyToOne(fetch = FetchType.LAZY)
        val user: User,

        @Enumerated(EnumType.STRING)
        val status: NotificationStatus = NotificationStatus.NEW
) : BaseEntity()