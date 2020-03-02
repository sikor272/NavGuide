package pl.umk.mat.locals.dto.out

import pl.umk.mat.locals.models.Agreement
import pl.umk.mat.locals.models.enumerations.Status
import java.util.*

data class AgreementDto(
        val id: Long,
        val description: String,
        val offer: OfferDto,
        val traveler: UserDto,
        val status: Status,
        val plannedDate: Date,
        val price: Float
) {
    constructor(agreement: Agreement) : this(
            id = agreement.id,
            description = agreement.description,
            offer = OfferDto(agreement.offer),
            traveler = UserDto(agreement.traveler),
            status = agreement.status,
            plannedDate = agreement.plannedDate,
            price = agreement.price
    )
}