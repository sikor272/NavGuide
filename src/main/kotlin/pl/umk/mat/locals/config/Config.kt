package pl.umk.mat.locals.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("locals")
data class Config(
        var exampleConfigurationString: String = "test",
        var secretKey: String = "kotNaPlecach"
)