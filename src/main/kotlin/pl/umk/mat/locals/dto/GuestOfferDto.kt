package pl.umk.mat.locals.dto

import pl.umk.mat.locals.models.Offer
import pl.umk.mat.locals.models.PriceType

data class GuestOfferDto(
        val id: Long,
        val name: String,
        val lat: Float,
        val lon: Float,
        val inSearch: Long,
        val price: Float,
        val priceType: PriceType,
        val tags: List<TagDto>
) {
    constructor(offer: Offer) : this(
            id = offer.id,
            name = offer.name,
            lat = offer.lat,
            lon = offer.lon,
            inSearch = offer.inSearch,
            price = offer.price,
            priceType = offer.priceType,
            tags = offer.tags.map {
                TagDto(it)
            }
    )
}
