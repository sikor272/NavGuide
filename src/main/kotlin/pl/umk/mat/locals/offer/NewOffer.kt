package pl.umk.mat.locals.offer

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.utils.enumerations.PriceType
import java.util.*
import javax.validation.constraints.Future
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class NewOffer(

        @field:NotBlank
        @field:Size(min = 1, max = 200)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val name: String,

        @field:NotBlank
        @field:Size(min = 1, max = 200)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val city: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val lat: Double,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val lon: Double,

        @field:ApiModelProperty(notes = "meter.", example = "1", required = true)
        val radius: Long,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val begin: Date,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        @field:Future
        val end: Date,

        @field:Min(1)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val maxPeople: Long,

        @field:Min(0)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val price: Float,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val priceType: PriceType,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val tags: String,

        @field:NotBlank
        @field:Size(min = 1, max = 2048)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val description: String
) {
    @get:ApiModelProperty(hidden = true)
    val tag: List<Long>
        get() = tags.split(",").map { it.toLong() }
}