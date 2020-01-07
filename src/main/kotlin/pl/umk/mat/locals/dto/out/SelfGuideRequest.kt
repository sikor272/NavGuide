package pl.umk.mat.locals.dto.out

import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.Enums.GuideRequestStatus
import pl.umk.mat.locals.models.Enums.Language
import pl.umk.mat.locals.models.GuideRequest
import java.util.*

data class SelfGuideRequest(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val date: Date,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val status: GuideRequestStatus,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val message: String?,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val description: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val languages: List<Language>
) {
    constructor(guideRequest: GuideRequest) :
            this(
                    date = guideRequest.createdAt,
                    status = guideRequest.status,
                    message = guideRequest.message,
                    description = guideRequest.description,
                    languages = guideRequest.languages
            )

}