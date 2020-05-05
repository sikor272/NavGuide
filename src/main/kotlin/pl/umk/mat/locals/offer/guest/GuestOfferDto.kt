package pl.umk.mat.locals.offer.guest

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.offer.tag.TagDto
import pl.umk.mat.locals.utils.enumerations.PriceType
import java.util.*

@ApiModel
data class GuestOfferDto(
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
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val photos: List<String>,
        @field:ApiModelProperty(notes = "meter.")
        val radius: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val description: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val averageMark: Double,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val inSearch: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val begin: Date,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val end: Date

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
            photos = offer.photos,
            radius = offer.radius,
            description = offer.description,
            averageMark = offer.feedbackOffers.map {
                it.scoreOffer
            }.average(),
            inSearch = offer.inSearch,
            begin = offer.begin,
            end = offer.end
    )
}
