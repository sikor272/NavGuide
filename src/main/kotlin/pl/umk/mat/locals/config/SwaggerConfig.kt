package pl.umk.mat.locals.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestMethod
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.ResponseMessage
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration::class)
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(AuthenticationPrincipal::class.java)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.umk.mat.locals"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.POST, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, defaultResponseMessage())
                .globalResponseMessage(RequestMethod.PATCH, defaultResponseMessage())
                .securitySchemes(listOf(apiKey()))

    }


    private fun apiKey(): ApiKey {
        return ApiKey("JWT Token", "Authorization", "header")
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Locals")
                .version("0.1.0")
                .description("Find your local guide with us!!!")
                .build()
    }

    private fun defaultResponseMessage(): List<ResponseMessage> {
        return listOf(
                ResponseMessageBuilder()
                        .code(500)
                        .message("Internal server error")
                        .build()
        )
    }
}