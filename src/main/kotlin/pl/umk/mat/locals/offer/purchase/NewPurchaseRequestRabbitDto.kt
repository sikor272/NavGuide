package pl.umk.mat.locals.offer.purchase

import java.io.Serializable

data class NewPurchaseRequestRabbitDto(
        val email: String,
        val firstName: String,
        val lastName: String,
        val offerName: String
) : Serializable