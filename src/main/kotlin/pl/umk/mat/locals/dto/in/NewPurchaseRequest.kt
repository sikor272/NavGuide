package pl.umk.mat.locals.dto.`in`

import java.util.*

data class NewPurchaseRequest(
        val message: String,
        val offerId: Long,
        val plannedDate: Date
)