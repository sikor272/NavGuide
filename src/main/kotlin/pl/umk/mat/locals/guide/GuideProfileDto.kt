package pl.umk.mat.locals.guide

import pl.umk.mat.locals.utils.enumerations.Language

data class GuideProfileDto(
        val languages: List<Language>,
        val lastName: String,
        val firstName: String,
        val guideId: Long,
        val userId: Long,
        val experience: Int,
        val averageMark: Double?
) {
    constructor(guideProfile: GuideProfile) : this(
            experience = guideProfile.experience,
            languages = guideProfile.languages,
            firstName = guideProfile.user.firstName,
            lastName = guideProfile.user.lastName,
            guideId = guideProfile.id,
            userId = guideProfile.user.id,
            averageMark = guideProfile.offers.flatMap { offer ->
                offer.feedbackOffers.map {
                    it.scoreGuide
                }
            }.average()
    )
}

