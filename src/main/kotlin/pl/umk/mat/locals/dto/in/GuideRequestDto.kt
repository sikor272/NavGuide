package pl.umk.mat.locals.dto.`in`

import pl.umk.mat.locals.models.enumerations.Experience
import pl.umk.mat.locals.models.enumerations.Language

data class GuideRequestDto(
        val languages: List<Language>,

        val experience: Experience,

        val description: String
)