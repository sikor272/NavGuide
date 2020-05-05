package pl.umk.mat.locals.guide

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.utils.enumerations.Language

@ApiModel
data class GuideProfileDto(

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val languages: List<Language>,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val lastName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val firstName: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val avatar: String,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val guideId: Long,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val userId: Long,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val experience: Int,

        @field:ApiModelProperty(notes = "It's exactly what you expect.")
        val averageMark: Double?

) {
    constructor(guideProfile: GuideProfile) : this(
            experience = guideProfile.experience,
            languages = guideProfile.languages,
            firstName = guideProfile.user.firstName,
            lastName = guideProfile.user.lastName,
            avatar = guideProfile.user.avatar,
            guideId = guideProfile.id,
            userId = guideProfile.user.id,
            averageMark = guideProfile.offers.flatMap { offer ->
                offer.feedbackOffers.map {
                    it.scoreGuide
                }
            }.average()
    )
}

