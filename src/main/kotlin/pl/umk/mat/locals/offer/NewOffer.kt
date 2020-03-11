package pl.umk.mat.locals.offer

import io.swagger.annotations.ApiModelProperty
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
        val tags: String,
        val description: String
) {
        val tag: List<Long>
                get() = tags.split(",").map { it.toLong() }
}