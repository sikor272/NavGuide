package pl.umk.mat.locals.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.umk.mat.locals.dto.ExampleDto
import pl.umk.mat.locals.dto.TagDto
import pl.umk.mat.locals.services.ExampleService
import pl.umk.mat.locals.services.TagService


@RestController
@RequestMapping("/tag")
class TagController(
        private val tagService: TagService
) {
    @GetMapping
    fun getAllTags(): List<TagDto> {
        return tagService.getAllTags()
    }

}