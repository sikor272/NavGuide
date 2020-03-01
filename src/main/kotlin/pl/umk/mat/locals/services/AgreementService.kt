package pl.umk.mat.locals.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.`in`.ChangeAgreementStatus
import pl.umk.mat.locals.dto.`in`.ChangeStatus
import pl.umk.mat.locals.dto.`in`.NewAgreement
import pl.umk.mat.locals.dto.out.AgreementDto
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.exceptions.UserAuthException
import pl.umk.mat.locals.models.Agreement
import pl.umk.mat.locals.models.BoughtOffer
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.models.enumerations.Status
import pl.umk.mat.locals.repositories.AgreementRepository
import pl.umk.mat.locals.repositories.BoughtOfferRepository
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.UserRepository
import java.util.*
import javax.transaction.Transactional

@Service
class AgreementService(
        private val agreementRepository: AgreementRepository,
        private val userRepository: UserRepository,
        private val offerRepository: OfferRepository,
        private val boughtOfferRepository: BoughtOfferRepository
) {
    @Transactional
    fun createNewAgreement(user: User, newAgreement: NewAgreement) {
        val target = userRepository.findByIdOrNull(newAgreement.userId)
                ?: throw ResourceNotFoundException("User dont found.")
        val offer = offerRepository.findByIdOrNull(newAgreement.offerId)
                ?: throw ResourceNotFoundException("Offer dont found.")
        if(user.guideProfile == null)
            throw ResourceNotFoundException("You are not a guide")
        agreementRepository.save(
                Agreement(
                        offer = offer,
                        target = target,
                        description = newAgreement.description,
                        plannedDate = newAgreement.plannedDate,
                        price = newAgreement.price
                )
        )
    }

    fun getOwnAgreements(user: User): List<AgreementDto> {
        return agreementRepository.getAllByAuthorOrTarget(
                user.guideProfile,
                user
        ).map { AgreementDto(it) }
    }

    fun changeAgreementStatus(agreementId: Long, user: User, changeAgreementStatus: ChangeAgreementStatus) {
        val agreement = agreementRepository.findByIdOrNull(agreementId)
                ?: throw  ResourceNotFoundException("Agreement dont found.")
        if (agreement.target != user) throw UserAuthException("You cannot accept/reject this agreement")
        agreementRepository.save(
                agreement.copy(
                        status = if (changeAgreementStatus.status == ChangeStatus.ACCEPT) {
                            Status.ACCEPTED
                        } else {
                            Status.REJECTED
                        }
                )
        )
        if (changeAgreementStatus.status == ChangeStatus.ACCEPT)
            boughtOfferRepository.save(
                    BoughtOffer(
                            offer = agreement.offer,
                            traveler = user,
                            plannedDate = agreement.plannedDate,
                            price = agreement.price
                    )
            )
    }

}