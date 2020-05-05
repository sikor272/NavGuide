package pl.umk.mat.locals.offer.agreement

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.utils.enumerations.ChangeStatus
import javax.validation.constraints.Size

@ApiModel
data class ChangeAgreementStatus(
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val status: ChangeStatus
)