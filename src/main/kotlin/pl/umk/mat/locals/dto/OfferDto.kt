package pl.umk.mat.locals.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Offer
import pl.umk.mat.locals.models.PriceType

@ApiModel(value = "Offer")
data class OfferDto(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val name: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val city: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lat: Double,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lon: Double,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val price: Float,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val priceType: PriceType,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val tags: List<TagDto>,
        @field:ApiModelProperty(notes = "meter.")
        val radius:Long
) {
    constructor(offer: Offer) : this(
            id = offer.id,
            name = offer.name,
            city = offer.city,
            lat = offer.lat,
            lon = offer.lon,
            price = offer.price,
            priceType = offer.priceType,
            tags = offer.tags.map {
                TagDto(it)
            },
            radius = offer.radius
    )
}
