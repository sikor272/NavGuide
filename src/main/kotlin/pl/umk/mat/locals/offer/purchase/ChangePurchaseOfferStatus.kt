package pl.umk.mat.locals.offer.purchase

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import pl.umk.mat.locals.utils.enumerations.ChangeStatus
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@ApiModel
data class ChangePurchaseOfferStatus(
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val status: ChangeStatus,

        @field:NotBlank
        @field:Size(min = 1, max = 2048)
        @field:ApiModelProperty(notes = "It's exactly what you expect.", required = true)
        val message: String
)