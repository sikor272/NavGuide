package pl.umk.mat.locals.offer.agreement

import java.util.*


data class NewAgreement(
        val description: String,
        val purchaseRequestId: Long,
        val plannedDate: Date,
        val price: Float
)