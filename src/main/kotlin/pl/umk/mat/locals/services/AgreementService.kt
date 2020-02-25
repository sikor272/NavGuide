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
import pl.umk.mat.locals.repositories.BoughtOffertRepository
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.UserRepository
import javax.transaction.Transactional

@Service
class AgreementService(
        private val agreementRepository: AgreementRepository,
        private val userRepository: UserRepository,
        private val offerRepository: OfferRepository,
        private val boughtOffertRepository: BoughtOffertRepository
) {
    @Transactional
    fun createNewAgreement(user: User, newAgreement: NewAgreement) {
        val target = userRepository.findByIdOrNull(newAgreement.userId)
                ?: throw ResourceNotFoundException("User dont found.")
        val offer = offerRepository.findByIdOrNull(newAgreement.offerId)
                ?: throw ResourceNotFoundException("Offer dont found.")
        agreementRepository.save(
                Agreement(
                        offer = offer,
                        target = target,
                        description = newAgreement.description,
                        author = user.guideProfile ?: throw ResourceNotFoundException("You are not a guide")
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
        boughtOffertRepository.save(
                BoughtOffer(
                        offer = agreement.offer,
                        user = user
                )
        )
    }

}