package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Country
import pl.umk.mat.locals.models.Experience

@ApiModel(value = "Confirm Google account")
data class ConfirmGoogleAccount(

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val email: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lastName: String,

        @field:ApiModelProperty(notes = "Country code ISO 3166-1 alpha-2")
        val country: Country,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val telephone: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val experience: Experience,

        @field:ApiModelProperty(notes = "List of interest ID")
        val interests: List<Long>
)