package pl.umk.mat.locals.offer.feedback

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User

@Repository
interface FeedbackRepository : CrudRepository<Feedback, Long> {
    fun findALLByOffer(offer: Offer): List<Feedback>
    fun findAllByAuthorAndOffer(user: User, offer: Offer): List<Feedback>
}