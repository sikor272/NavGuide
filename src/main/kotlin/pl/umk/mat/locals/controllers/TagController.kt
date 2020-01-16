package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.`in`.NewTag
import pl.umk.mat.locals.dto.out.TagDto
import pl.umk.mat.locals.services.AdministratorService
import pl.umk.mat.locals.services.TagService
import javax.validation.Valid


@RestController
@RequestMapping("/tags")
@Api(tags = ["Tag Controller"], description = "This controller is used to manage available tags for trips.")
class TagController(
        private val tagService: TagService,
        private val administratorService: AdministratorService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all available tags.")
    fun getAllTags(): List<TagDto> {
        return tagService.getAllTags()
    }

    @PostMapping
    @ApiOperation("Add tag (used in offers).", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addNewTag(
            @RequestBody @Valid newTag: NewTag
    ) {
        administratorService.addNewTag(newTag)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove tag (used in offers).", authorizations = [Authorization("JWT Token")])
    fun deleteTag(
            @PathVariable id: Long
    ) {
        administratorService.deleteTag(id)
    }

}