package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Country
import pl.umk.mat.locals.models.Experience
import pl.umk.mat.locals.models.Gender
import pl.umk.mat.locals.validators.annotations.Telephone
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class NewUserData(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        @field:Email
        val email: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        @field:NotBlank(message = "First name cannot be empty!")
        val firstName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        @field:NotBlank(message = "Last name cannot be empty!")
        val lastName: String,

        @field:ApiModelProperty(notes = "Country code ISO 3166-1 alpha-2")
        val country: Country,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        @field:Telephone
        val telephone: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val experience: Experience,

        @field:ApiModelProperty(notes = "List of interest ID")
        val interests: List<Long>,
        val gender: Gender

)