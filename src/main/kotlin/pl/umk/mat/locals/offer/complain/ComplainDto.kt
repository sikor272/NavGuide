package pl.umk.mat.locals.offer.complain

import io.swagger.annotations.ApiModel

@ApiModel(value = "Complain")
data class ComplainDto(
        val id: Long
) {
    constructor(complain: Complain) : this(id = complain.id)
}