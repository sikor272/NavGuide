package pl.umk.mat.locals.offer.agreement

import org.springframework.stereotype.Service
import pl.umk.mat.locals.offer.OfferRepository
import pl.umk.mat.locals.offer.bought.BoughtOffer
import pl.umk.mat.locals.offer.bought.BoughtOfferRepository
import pl.umk.mat.locals.offer.purchase.PurchaseRequestRepository
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.enumerations.ChangeStatus
import pl.umk.mat.locals.utils.enumerations.Status
import pl.umk.mat.locals.utils.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.utils.exceptions.UserAuthException
import pl.umk.mat.locals.utils.findByIdOrThrow
import java.util.*
import javax.transaction.Transactional

@Service
class AgreementService(
        private val agreementRepository: AgreementRepository,
        private val userRepository: UserRepository,
        private val offerRepository: OfferRepository,
        private val boughtOfferRepository: BoughtOfferRepository,
        private val purchaseRequestRepository: PurchaseRequestRepository
) {
    @Transactional
    fun createNewAgreement(user: User, newAgreement: NewAgreement) {
        val target = purchaseRequestRepository.findByIdOrThrow(newAgreement.purchaseRequestId)
        if (user.guideProfile == null)
            throw ResourceNotFoundException("You are not a guide")
        agreementRepository.save(
                Agreement(
                        offer = target.offer,
                        traveler = target.traveler,
                        description = newAgreement.description,
                        plannedDate = newAgreement.plannedDate,
                        price = newAgreement.price,
                        purchaseRequest = target
                )
        )
    }

    fun getOwnAgreements(user: User): List<AgreementDto> {
        return agreementRepository.getAllByOfferOwnerOrTraveler(
                user.guideProfile,
                user
        ).filter {
            it.plannedDate >= Date() || it.status == Status.PENDING
        }.map { AgreementDto(it) }
    }

    fun changeAgreementStatus(agreementId: Long, user: User, changeAgreementStatus: ChangeAgreementStatus) {
        val agreement = agreementRepository.findByIdOrThrow(agreementId)
        if (agreement.traveler.id != user.id) throw UserAuthException("You cannot accept/reject this agreement")
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
                            price = agreement.price,
                            guide = agreement.offer.owner
                    )
            )
    }

}