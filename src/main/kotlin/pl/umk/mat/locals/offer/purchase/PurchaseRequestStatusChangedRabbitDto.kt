package pl.umk.mat.locals.offer.purchase

import pl.umk.mat.locals.utils.enumerations.Status
import java.io.Serializable

data class PurchaseRequestStatusChangedRabbitDto(
        val email: String,
        val firstName: String,
        val lastName: String,
        val guide: Guide,
        val offerName: String,
        val status: Status
) : Serializable {
    data class Guide(
            val firstName: String,
            val lastName: String
    )
}