package pl.umk.mat.locals.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.umk.mat.locals.dto.ExampleDto
import pl.umk.mat.locals.services.ExampleService
import springfox.documentation.annotations.ApiIgnore


@RestController
@RequestMapping("/example")
@ApiIgnore
class ExampleController(
        private val exampleService: ExampleService
) {
    @GetMapping
    fun getAllExamples(): List<ExampleDto> {
        return exampleService.getAllExamples()
    }

    @GetMapping("/{id}")
    fun getExampleById(@PathVariable id: Long): ExampleDto {
        return exampleService.getExampleById(id)
    }
}