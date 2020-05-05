package pl.umk.mat.locals.offer.complain

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel
data class ComplainDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long
) {
    constructor(complain: Complain) : this(id = complain.id)
}