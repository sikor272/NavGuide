package pl.umk.mat.locals.offer.tag

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

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

