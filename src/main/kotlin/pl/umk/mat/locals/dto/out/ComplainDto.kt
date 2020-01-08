package pl.umk.mat.locals.dto.out

import io.swagger.annotations.ApiModel
import pl.umk.mat.locals.models.Complain

@ApiModel(value = "Complain")
data class ComplainDto(
        val id: Long
) {
    constructor(complain: Complain) : this(id = complain.id)
}