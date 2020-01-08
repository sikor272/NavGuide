package pl.umk.mat.locals.services


import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.out.TagDto
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