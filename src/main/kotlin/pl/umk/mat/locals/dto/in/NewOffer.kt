package pl.umk.mat.locals.dto.`in`

import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Enums.PriceType
import java.util.*

data class NewOffer(
        val name: String,
        val city: String,
        val lat: Double,
        val lon: Double,
        @field:ApiModelProperty(notes = "meter.", example = "1")
        val radius: Long,
        val begin: Date,
        val end: Date,
        val maxPeople: Long,
        val price: Float,
        val priceType: PriceType,
        val tags: List<Long>
)