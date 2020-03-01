package pl.umk.mat.locals.services


import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.`in`.NewComplain
import pl.umk.mat.locals.dto.out.TagDto
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.models.Complain
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.repositories.ComplainRepository
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.TagRepository
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
                        target = offerRepository.findByIdOrNull(complain.offerId)
                                ?: throw ResourceNotFoundException("Offer not found."),
                        description = complain.description,
                        author = user
                )
        )
    }
}