package pl.umk.mat.locals.offer.bought

import org.springframework.stereotype.Service
import pl.umk.mat.locals.guide.GuideProfileRepository
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
        private val boughtOfferRepository: BoughtOfferRepository
) {

    fun getOfferHistory(userId: Long, questioningUser: User): List<BoughtOfferDto> {
        val user = userRepository.findByIdOrThrow(userId)
        if (questioningUser.role == Role.ADMIN ||
                user.role == Role.GUIDE ||
                questioningUser.id == user.id ||
                (questioningUser.guideProfile != null &&
                        purchaseRequestRepository.existsByTravelerAndOffer_Owner(user, questioningUser.guideProfile)))
            return user.boughtOffers.map { BoughtOfferDto(it) } // TODO
        throw AuthException("You don't have permission to display users!")
        /*
        if (questioningUser.role == Role.ADMIN) {
            return user.boughtOffers.map { BoughtOfferDto(it) }
        }

        if (user == questioningUser) {
            return user.boughtOffers.map { BoughtOfferDto(it) }
        }

        if (questioningUser.guideProfile != null && purchaseRequestRepository.existsByTravelerAndOffer_Owner(user, questioningUser.guideProfile)) {
            return user.boughtOffers.map { BoughtOfferDto(it) }
        }

        throw AuthException("You don't have permission to display users!")*/
    }

    fun getOfferHistoryAsGuide(guideId: Long): List<BoughtOfferDto> {
        val guide = guideProfileRepository.findByIdOrThrow(guideId)
        return boughtOfferRepository.findAllByGuide(guide).map { BoughtOfferDto(it) }
    }

}