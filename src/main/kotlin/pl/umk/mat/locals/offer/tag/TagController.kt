package pl.umk.mat.locals.offer.tag

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/tags")
@Api(tags = ["Tag Controller"], description = "This controller is used to manage available tags for trips.")
class TagController(
        private val tagService: TagService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all available tags.")
    fun getAllTags(): List<TagDto> {
        return tagService.getAllTags()
    }

    @PostMapping
    @ApiOperation("Add tag (Administrator only).", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addNewTag(
            @RequestBody @Valid newTag: NewTag
    ) {
        tagService.addNewTag(newTag)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove tag (Administrator only).", authorizations = [Authorization("JWT Token")])
    fun deleteTag(
            @PathVariable id: Long
    ) {
        tagService.deleteTag(id)
    }

}