package pl.umk.mat.locals.guide

import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.utils.enumerations.Language

data class GuideProfileDto(
        val languages: List<Language>,
        val lastName: String,
        val firstName: String,
        val guideId: Long,
        val userId: Long,
        val experience: Int,
        val averageMark: Double
) {
    constructor(guideProfile: GuideProfile, averageGuideMark: Double) : this(
            experience = guideProfile.experience,
            languages = guideProfile.languages,
            firstName = guideProfile.guideRequest.user.firstName,
            lastName = guideProfile.guideRequest.user.lastName,
            guideId = guideProfile.id,
            userId = guideProfile.guideRequest.user.id,
            averageMark = averageGuideMark
    )
}

