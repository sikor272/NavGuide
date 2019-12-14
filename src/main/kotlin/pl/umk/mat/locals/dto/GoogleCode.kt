package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Google code")
data class GoogleCode(
        @field:ApiModelProperty("One time code provided by Google.")
        val code: String
)