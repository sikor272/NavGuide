package pl.umk.mat.locals.dto.out

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.models.GuideRequest
import pl.umk.mat.locals.models.enumerations.Status
import pl.umk.mat.locals.models.enumerations.Language
import java.util.*


@ApiModel(value = "Guide Request for Administrator")
data class AdministratorGuideRequest(
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val id: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val date: Date,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val status: Status,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val message: String?,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val description: String,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val languages: List<Language>,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val userId: Long,
        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val experience: Int
) {
    constructor(guideRequest: GuideRequest) :
            this(
                    date = guideRequest.createdAt,
                    status = guideRequest.status,
                    message = guideRequest.message,
                    description = guideRequest.description,
                    languages = guideRequest.languages,
                    id = guideRequest.id,
                    userId = guideRequest.user.id,
                    experience = guideRequest.experience
            )

}
