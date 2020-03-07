package pl.umk.mat.locals.guide.request

import pl.umk.mat.locals.utils.enumerations.ChangeStatus


data class ChangeGuideRequestStatus(
        val message: String?,
        val guideRequestStatus: ChangeStatus
)