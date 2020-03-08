package pl.umk.mat.locals.auth.admin

import io.swagger.annotations.ApiModel
import java.util.*

@ApiModel(value = "Ban")
data class Ban(
        val end: Date
)