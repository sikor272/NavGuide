package pl.umk.mat.locals.dto

import pl.umk.mat.locals.models.PriceType
import java.util.*

data class NewOffer(
        val name: String,
        val city: String,
        val lat: Float,
        val lon: Float,
        val radius: Float,
        val begin: Date,
        val end: Date,
        val maxPeople: Long,
        val price: Float,
        val priceType: PriceType,
        val tags: List<Long>
)