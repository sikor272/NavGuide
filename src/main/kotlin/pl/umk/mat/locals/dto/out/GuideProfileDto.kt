package pl.umk.mat.locals.dto.out

import pl.umk.mat.locals.models.GuideProfile
import pl.umk.mat.locals.models.enumerations.Language

data class GuideProfileDto(
        val languages: List<Language>,
        val lastName: String,
        val firstName: String
) {
    constructor(guideProfile: GuideProfile) : this(

            languages = guideProfile.languages,
            firstName = guideProfile.user.firstName,
            lastName = guideProfile.user.lastName
    )
}

