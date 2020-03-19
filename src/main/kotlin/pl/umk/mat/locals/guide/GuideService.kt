package pl.umk.mat.locals.guide


import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.offer.bought.BoughtOfferDto
import pl.umk.mat.locals.utils.findByIdOrThrow

@Service
class GuideService(
        private val guideProfileRepository: GuideProfileRepository
) {

    fun getGuideProfile(id: Long): GuideProfileDto {
        return GuideProfileDto(guideProfileRepository.findByIdOrThrow(id))
    }

    fun getAllGuideOffers(id: Long): List<OfferDto> {
        return guideProfileRepository.findByIdOrThrow(id).offers.map { OfferDto(it) }
    }

    fun getGuideHistoryOffer(guideId: Long): List<BoughtOfferDto> {
        return guideProfileRepository.findByIdOrThrow(guideId).user.boughtOffers.map { BoughtOfferDto(it) }
    }
}