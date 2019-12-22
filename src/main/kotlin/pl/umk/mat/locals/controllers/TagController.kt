package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.umk.mat.locals.dto.TagDto
import pl.umk.mat.locals.services.TagService


@RestController
@RequestMapping("/tags")
@Api(tags = ["Tag Controller"], description = "This controller is used to managed available tags for trips.")
class TagController(
        private val tagService: TagService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all available tags.")
    fun getAllTags(): List<TagDto> {
        return tagService.getAllTags()
    }

}