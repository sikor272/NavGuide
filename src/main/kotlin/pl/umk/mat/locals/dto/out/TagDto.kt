package pl.umk.mat.locals.dto.out

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Tag

@ApiModel(value = "Tag")
data class TagDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val name: String
) {
    constructor(tag: Tag) : this(
            id = tag.id,
            name = tag.name
    )
}

