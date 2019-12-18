package pl.umk.mat.locals.dto

import pl.umk.mat.locals.models.Interest

data class InterestDto(
        val id: Long,
        val name: String
) {
    constructor(interest: Interest) : this(
            id = interest.id,
            name = interest.name
    )

}