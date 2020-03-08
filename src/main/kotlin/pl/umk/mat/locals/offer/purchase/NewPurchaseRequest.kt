package pl.umk.mat.locals.offer.purchase

import java.util.*

data class NewPurchaseRequest(
        val message: String,
        val offerId: Long,
        val plannedDate: Date
)