package pl.umk.mat.locals.dto.out

import pl.umk.mat.locals.models.Agreement
import pl.umk.mat.locals.models.User

data class AgreementDto(
        val id: Long,
        val description: String

){
    constructor(agreement: Agreement) : this(
            id = agreement.id,
            description = agreement.description
    )
}