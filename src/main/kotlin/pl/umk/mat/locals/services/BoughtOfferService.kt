package pl.umk.mat.locals.services


import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.out.BoughtOfferDto
import pl.umk.mat.locals.dto.out.OfferDto
import pl.umk.mat.locals.dto.out.TagDto
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.models.enumerations.Role
import pl.umk.mat.locals.repositories.PurchaseRequestRepository
import pl.umk.mat.locals.repositories.TagRepository
import pl.umk.mat.locals.repositories.UserRepository
import javax.security.auth.message.AuthException

@Service
class BoughtOfferService(
        private val userRepository: UserRepository,
        private val purchaseRequestRepository: PurchaseRequestRepository
) {

    fun findOffersHistoryByUserId(id: Long, questioningUser: User): List<BoughtOfferDto> {

        val user = userRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("User not found")
        if (questioningUser.role != Role.TRAVELER || questioningUser == user) {
            return user.boughtOffers.map { BoughtOfferDto(it) }.toList()
        }

        if (purchaseRequestRepository.existsByTravelerAndUserGuide(user, questioningUser)) {
            return user.boughtOffers.map { BoughtOfferDto(it) }.toList()
        }

        throw AuthException("You don't have permission to display users!")
    }


}