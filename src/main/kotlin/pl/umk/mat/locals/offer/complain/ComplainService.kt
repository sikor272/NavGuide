package pl.umk.mat.locals.offer.complain


import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferRepository
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.findByIdOrThrow
import javax.transaction.Transactional

@Service
class ComplainService(
        private val complainRepository: ComplainRepository,
        private val offerRepository: OfferRepository
) {

    @Transactional
    fun addNewComplain(complain: NewComplain, user: User) {
        complainRepository.save(
                Complain(
                        offer = offerRepository.findByIdOrThrow(complain.offerId),
                        description = complain.description,
                        author = user
                )
        )
    }

    @Transactional
    fun deleteComplain(id: Long) {
        val complain = complainRepository.findByIdOrThrow(id)
        complainRepository.delete(complain)
    }

    fun getAllComplains(): List<ComplainDto> {
        return complainRepository.findAll().asSequence().map { ComplainDto(it) }.toList()
    }
}