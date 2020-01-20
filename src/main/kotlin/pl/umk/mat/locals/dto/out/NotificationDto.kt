package pl.umk.mat.locals.dto.out

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Notification
import pl.umk.mat.locals.models.enumerations.NotificationStatus
import java.util.*

@ApiModel(value = "Notification")
data class NotificationDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val name: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val description: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val status: NotificationStatus,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val date: Date

) {
    constructor(notification: Notification) : this(
            id = notification.id,
            name = notification.name,
            description = notification.description,
            status = notification.status,
            date = notification.date
    )
}

