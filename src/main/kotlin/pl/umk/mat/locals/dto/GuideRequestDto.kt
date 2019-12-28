package pl.umk.mat.locals.dto

import pl.umk.mat.locals.models.Experience
import pl.umk.mat.locals.models.Language

data class GuideRequestDto(
        val languages: List<Language>,

        val experience: Experience,

        val description: String
)