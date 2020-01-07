package pl.umk.mat.locals.dto.`in`

import pl.umk.mat.locals.models.Enums.Experience
import pl.umk.mat.locals.models.Enums.Language

data class GuideRequestDto(
        val languages: List<Language>,

        val experience: Experience,

        val description: String
)