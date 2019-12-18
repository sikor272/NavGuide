package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Experience

@ApiModel(value = "Confirm Google account")
data class ConfirmGoogleAccount(

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val email: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lastName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val country: String,
        val telephone: String,
        val experience: Experience,
        val interests: List<Long>
)