package pl.umk.mat.locals.guide.request

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.utils.enumerations.ChangeStatus
import javax.validation.constraints.Size

@ApiModel
data class ChangeGuideRequestStatus(

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = false)
        @field:Size(max = 2048)
        val message: String?,

        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val guideRequestStatus: ChangeStatus
)