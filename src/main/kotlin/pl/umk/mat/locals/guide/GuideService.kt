package pl.umk.mat.locals.guide


import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.offer.OfferRepository
import pl.umk.mat.locals.offer.bought.BoughtOfferRepository
import pl.umk.mat.locals.offer.feedback.FeedbackRepository
import pl.umk.mat.locals.utils.findByIdOrThrow

@Service
class GuideService(
        private val guideProfileRepository: GuideProfileRepository,
        private val feedbackRepository: FeedbackRepository,
        private val boughtOfferRepository: BoughtOfferRepository,
        private val offerRepository: OfferRepository
) {

    fun getGuideProfile(id: Long): GuideProfileDto {
        val guide = guideProfileRepository.findByIdOrThrow(id)
        return GuideProfileDto(guide, feedbackRepository.findAllByOfferOwner(guide).map { it.scoreGuide }.average())
    }

    fun getAllGuideOffers(id: Long): List<OfferDto> {
        val guide = guideProfileRepository.findByIdOrThrow(id)
        return offerRepository.findAllByOwner(guide).map {
            OfferDto(it, feedbackRepository.findAllByOfferOwner(it.owner).map { score-> score.scoreGuide }.average(),
                feedbackRepository.findALLByOffer(it).map { score-> score.scoreOffer }.average(),
                boughtOfferRepository.findAllByOffer(it).count())
        }
    }
}