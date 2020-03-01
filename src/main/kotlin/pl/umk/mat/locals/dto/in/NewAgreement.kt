package pl.umk.mat.locals.dto.`in`

import java.util.*


data class NewAgreement(
        val offerId: Long,
        val description: String,
        val userId: Long,
        val plannedDate: Date,
        val price: Float
)