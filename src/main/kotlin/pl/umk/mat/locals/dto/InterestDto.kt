package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Interest

@ApiModel(value = "Interest")
data class InterestDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val name: String
) {
    constructor(interest: Interest) : this(
            id = interest.id,
            name = interest.name
    )

}