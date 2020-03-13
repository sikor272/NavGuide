package pl.umk.mat.locals.offer.bought

import org.springframework.stereotype.Service
import pl.umk.mat.locals.guide.GuideProfileRepository
import pl.umk.mat.locals.offer.feedback.FeedbackRepository
import pl.umk.mat.locals.offer.purchase.PurchaseRequestRepository
import pl.umk.mat.locals.user.Role
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.findByIdOrThrow
import javax.security.auth.message.AuthException

@Service
class BoughtOfferService(
        private val userRepository: UserRepository,
        private val purchaseRequestRepository: PurchaseRequestRepository,
        private val guideProfileRepository: GuideProfileRepository,
        private val boughtOfferRepository: BoughtOfferRepository,
        private val feedbackRepository: FeedbackRepository
) {

    fun getOfferHistoryAsTraveler(userId: Long, questioningUser: User): List<BoughtOfferDto> {

        val user = userRepository.findByIdOrThrow(userId)
        val guide = guideProfileRepository.findByGuideRequestUser(user)

        if (questioningUser.role == Role.ADMIN ||
                user == questioningUser ||
                (guide != null && purchaseRequestRepository.existsByTravelerAndOfferOwner(user, guide))
            ) {
            return boughtOfferRepository.findAllByTraveler(user).asSequence().map {
                BoughtOfferDto(it, feedbackRepository.findAllByOfferOwner(it.offer.owner).map {
                    score-> score.scoreGuide
                }.average(),
                    feedbackRepository.findALLByOffer(it.offer).map {
                        score-> score.scoreOffer
                    }.average(),
                    boughtOfferRepository.findAllByOffer(it.offer).count()) }.toList()
        }

        throw AuthException("You don't have permission to display users!")
    }

    fun getOfferHistoryAsGuide(guideId: Long): List<BoughtOfferDto> {
        val guide = guideProfileRepository.findByIdOrThrow(guideId)
        return boughtOfferRepository.findAllByOfferOwner(guide).asSequence().map {
            BoughtOfferDto(it, feedbackRepository.findAllByOfferOwner(it.offer.owner).map {
                score-> score.scoreGuide
            }.average(),
                    feedbackRepository.findALLByOffer(it.offer).map {
                        score-> score.scoreOffer
                    }.average(),
                    boughtOfferRepository.findAllByOffer(it.offer).count()) }.toList()
    }

}