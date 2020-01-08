package pl.umk.mat.locals.dto.`in`

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

@ApiModel(value = "Google code")
data class GoogleCode(
        @field:ApiModelProperty("One time code provided by Google.")
        @field:NotBlank(message = "Code cannot blank!")
        val code: String,

        @field:ApiModelProperty("Url")
        val request: String
)