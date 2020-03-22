package pl.umk.mat.locals.guide.request

import pl.umk.mat.locals.utils.enumerations.Status
import java.io.Serializable

data class GuideRequestStatusChangedRabbitDto(
        val email: String,
        val firstName: String,
        val lastName: String,
        val status: Status,
        val oneSignalId: String? = null
) : Serializable