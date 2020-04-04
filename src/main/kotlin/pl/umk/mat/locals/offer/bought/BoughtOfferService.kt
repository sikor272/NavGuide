package pl.umk.mat.locals.offer.bought

import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.purchase.PurchaseRequestRepository
import pl.umk.mat.locals.user.Role
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.findByIdOrThrow
import java.util.*
import javax.security.auth.message.AuthException

@Service
class BoughtOfferService(
        private val userRepository: UserRepository,
        private val purchaseRequestRepository: PurchaseRequestRepository
) {

    fun getOfferHistory(userId: Long, questioningUser: User): List<BoughtOfferDto> {
        val user = userRepository.findByIdOrThrow(userId)
        if (questioningUser.role == Role.ADMIN ||
                user.role == Role.GUIDE ||
                questioningUser.id == user.id ||
                (questioningUser.guideProfile != null &&
                        purchaseRequestRepository.existsByTravelerAndOffer_Owner(user, questioningUser.guideProfile)))
            return user.boughtOffers.filter {
                it.plannedDate <= Date()
            }.map { BoughtOfferDto(it) }
        throw AuthException("You don't have permission to display users!")
    }

}