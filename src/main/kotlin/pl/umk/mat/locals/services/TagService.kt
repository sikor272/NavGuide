package pl.umk.mat.locals.services


import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.ExampleDto
import pl.umk.mat.locals.dto.TagDto
import pl.umk.mat.locals.exceptions.ExampleNotFoundException
import pl.umk.mat.locals.repositories.ExampleRepository
import pl.umk.mat.locals.repositories.TagRepository

@Service
class TagService(
        private val tagRepository: TagRepository
) {

    fun getAllTags(): List<TagDto> {
        return tagRepository.findAll()
                .asSequence()
                .map {
                    TagDto(it)
                }.toList()
    }


}