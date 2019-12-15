package pl.umk.mat.locals.dto

import pl.umk.mat.locals.models.Tag

data class TagDto(
        val id: Long,
        val name: String
) {
    constructor(tag: Tag) : this(
            id = tag.id,
            name = tag.name
    )
}

