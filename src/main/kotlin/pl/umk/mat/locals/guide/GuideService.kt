package pl.umk.mat.locals.guide


import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferDto
import pl.umk.mat.locals.offer.bought.BoughtOfferDto
import pl.umk.mat.locals.offer.bought.BoughtOfferRepository
import pl.umk.mat.locals.utils.findByIdOrThrow
import java.util.*

@Service
class GuideService(
        private val guideProfileRepository: GuideProfileRepository,
        private val boughtOfferRepository: BoughtOfferRepository
) {

    fun getGuideProfile(id: Long): GuideProfileDto {
        return GuideProfileDto(guideProfileRepository.findByIdOrThrow(id))
    }

    fun getAllGuideOffers(id: Long): List<OfferDto> {
        return guideProfileRepository.findByIdOrThrow(id).offers.map { OfferDto(it) }
    }

    fun getGuideHistoryOffer(guideId: Long): List<BoughtOfferDto> {
        return boughtOfferRepository.findAllByOfferOwner(guideProfileRepository.findByIdOrThrow(guideId)).filter {
            it.date <= Date()
        }.map {
            BoughtOfferDto(it)
        }
    }
}