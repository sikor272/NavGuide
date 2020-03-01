package pl.umk.mat.locals.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.`in`.ChangePurchaseOfferStatus
import pl.umk.mat.locals.dto.`in`.ChangePurchaseOfferStatusEnum
import pl.umk.mat.locals.dto.`in`.NewComplain
import pl.umk.mat.locals.dto.`in`.NewPurchaseRequest
import pl.umk.mat.locals.dto.out.OfferDto
import pl.umk.mat.locals.dto.out.PurchaseRequestDto
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.exceptions.UserAuthException
import pl.umk.mat.locals.models.Complain
import pl.umk.mat.locals.models.PurchaseRequest
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.models.enumerations.Role
import pl.umk.mat.locals.models.enumerations.Status
import pl.umk.mat.locals.repositories.ComplainRepository
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.PurchaseRequestRepository
import pl.umk.mat.locals.repositories.UserRepository
import javax.security.auth.message.AuthException
import javax.transaction.Transactional

@Service

class OfferService(
        private val offerRepository: OfferRepository,
        private val complainRepository: ComplainRepository,
        private val purchaseRequestRepository: PurchaseRequestRepository,
        private val userRepository: UserRepository

) {
    fun getAllOffersByGeoLocalization(lat: Double, lon: Double, radius: Long): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findAllOffersByPoint(lat, lon, radius).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }

    fun getAllOffersByCity(city: String): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findAllOffersByCity(city).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }

    fun getRandomOffers(): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findRandomOffers().map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }

    fun getOfferById(id: Long): OfferDto {
        return OfferDto(offerRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Offert not found"))
    }

    fun getAllOffersByName(name: String): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findAllByNameContaining(name).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }

}
