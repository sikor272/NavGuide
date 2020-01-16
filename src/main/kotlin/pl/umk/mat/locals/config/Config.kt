package pl.umk.mat.locals.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("locals")
data class Config(
        var secretKey: String = "kotNaPlecach",
        var imageDir: String = "src/main/resources/static/",
        var imageServerUrl: String = "/",
        var imageRegex: String = "jpeg|jpg|png",
        var imageServerHost: String = ""
)