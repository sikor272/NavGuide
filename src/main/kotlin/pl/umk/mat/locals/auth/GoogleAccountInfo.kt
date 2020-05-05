package pl.umk.mat.locals.auth

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.utils.enumerations.Country

@ApiModel
data class GoogleAccountInfo(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val email: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lastName: String,

        @field:ApiModelProperty(notes = "Country code ISO 3166-1 alpha-2")
        val country: Country,

        @field:ApiModelProperty(notes = "This token will be use to authorization.")
        val authorizationToken: String
)