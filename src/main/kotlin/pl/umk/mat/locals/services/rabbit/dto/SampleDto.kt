package pl.umk.mat.locals.services.rabbit.dto

import java.io.Serializable

data class SampleDto(
        val email: String,
        val firstname: String,
        val lastname: String
) : Serializable