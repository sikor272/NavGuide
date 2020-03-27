package pl.umk.mat.locals.offer.feedback

import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferRepository
import pl.umk.mat.locals.offer.bought.BoughtOfferRepository
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.exceptions.ResourceAlreadyExistException
import pl.umk.mat.locals.utils.exceptions.UserAuthException
import pl.umk.mat.locals.utils.findByIdOrThrow

@Service
class FeedbackService(
        private val feedbackRepository: FeedbackRepository,
        private val boughtOfferRepository: BoughtOfferRepository,
        private val offerRepository: OfferRepository
) {

    fun addFeedback(feedback: NewFeedback, traveler: User) {
        val offer = offerRepository.findByIdOrThrow(feedback.offerId)
        if (boughtOfferRepository.existsByTravelerAndOffer(traveler, offer)) {
            val test = feedbackRepository.findAllByAuthorAndOffer(traveler, offer)
            if(test.isNotEmpty())
                    throw ResourceAlreadyExistException("You can't feedback again this offer")

            feedbackRepository.save(Feedback(
                    author = traveler,
                    offer = offer,
                    scoreOffer = feedback.scoreOffer,
                    scoreGuide = feedback.scoreGuide,
                    comment = feedback.comment
            ))

        } else {
            throw UserAuthException("You can't comment this offer")
        }
    }

}