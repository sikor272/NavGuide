package pl.umk.mat.locals.guide.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.utils.enumerations.Language
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class GuideRequestDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val languages: List<Language>,

        @field:Min(1)
        @field:Max(5)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val experience: Int,

        @field:NotBlank
        @field:Size(min = 1, max = 2048)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val description: String
)