package pl.umk.mat.locals.offer

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.guide.GuideProfileDto
import pl.umk.mat.locals.offer.tag.TagDto

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
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val owner: GuideProfileDto,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val maxPeople: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val photos: List<String>,
        @field:ApiModelProperty(notes = "meter.")
        val radius: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val description: String
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
            owner = GuideProfileDto(offer.owner),
            maxPeople = offer.maxPeople,
            photos = offer.photos,
            radius = offer.radius,
            description = offer.description
    )
}
