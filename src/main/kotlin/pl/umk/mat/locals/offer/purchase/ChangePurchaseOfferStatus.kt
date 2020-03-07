package pl.umk.mat.locals.offer.purchase

import pl.umk.mat.locals.utils.enumerations.ChangeStatus

data class ChangePurchaseOfferStatus(
        val status: ChangeStatus,
        val message: String
)