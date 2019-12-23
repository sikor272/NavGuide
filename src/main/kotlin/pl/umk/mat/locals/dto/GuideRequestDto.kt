package pl.umk.mat.locals.dto

import pl.umk.mat.locals.models.Experience

data class GuideRequestDto(
        val languages: List<String>,

        val experience: Experience,

        val description: String
)