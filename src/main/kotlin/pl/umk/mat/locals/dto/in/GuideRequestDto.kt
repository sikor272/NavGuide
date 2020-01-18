package pl.umk.mat.locals.dto.`in`

import org.hibernate.validator.constraints.Range
import pl.umk.mat.locals.models.enumerations.Language

data class GuideRequestDto(
        val languages: List<Language>,

        @field:Range(min = 1, max = 5)
        val experience: Int,

        val description: String
)