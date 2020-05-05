package pl.umk.mat.locals.message

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.user.UserDto
import java.util.*

@ApiModel
data class MessageDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val author: UserDto,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val description: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val date: Date

) {
    constructor(message: Message) : this(
            id = message.id,
            author = UserDto(message.author),
            description = message.description,
            date = message.date
    )
}

