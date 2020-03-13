package pl.umk.mat.locals.offer.feedback

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.offer.Offer

@Repository
interface FeedbackRepository : CrudRepository<Feedback, Long> {
    fun findALLByOffer(offer: Offer): List<Feedback>
    fun findAllByOfferOwner(guideProfile: GuideProfile): List<Feedback>
}