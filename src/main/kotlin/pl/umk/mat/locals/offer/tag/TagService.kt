package pl.umk.mat.locals.offer.tag


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.umk.mat.locals.offer.OfferRepository
import pl.umk.mat.locals.utils.findByIdOrThrow

@Service
class TagService(
        private val tagRepository: TagRepository,
        private val offerRepository: OfferRepository
) {

    fun getAllTags(): List<TagDto> {
        return tagRepository.findAll()
                .asSequence()
                .map {
                    TagDto(it)
                }.toList()
    }


    fun addNewTag(newTag: NewTag) {
        tagRepository.save(
                Tag(
                        name = newTag.name
                )
        )
    }


    @Transactional
    fun deleteTag(id: Long) {
        val tag = tagRepository.findByIdOrThrow(id)
        offerRepository.saveAll(tag.offers.map { it.copy(tags = it.tags - tag) })
        tagRepository.delete(tag)
    }

}