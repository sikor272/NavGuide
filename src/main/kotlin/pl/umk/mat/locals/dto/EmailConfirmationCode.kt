package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Confirm Email")
data class EmailConfirmationCode(
        @field:ApiModelProperty(notes = "Code received on email.")
        val code: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val email: String
)