package pl.umk.mat.locals.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.umk.mat.locals.dto.*
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.models.*
import pl.umk.mat.locals.repositories.*

@Service
class AdministratorService(
        private val tagRepository: TagRepository,
        private val guideRequestRepository: GuideRequestRepository,
        private val userRepository: UserRepository,
        private val interestRepository: InterestRepository,
        private val complainRepository: ComplainRepository,
        private val guideProfileRepository: GuideProfileRepository,
        private val offerRepository: OfferRepository
) {

    fun getAllPendingGuideRequests(): List<AdministratorGuideRequest> {
        return guideRequestRepository.getAllByStatus(GuideRequestStatus.PENDING).map {
            AdministratorGuideRequest(it)
        }
    }

    @Transactional
    fun acceptGuideRequest(id: Long, changeGuideRequestStatus: ChangeGuideRequestStatus) {
        val guideRequest = guideRequestRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Guide request not found.")
        userRepository.save(
                guideRequest.user.copy(
                        role = Role.GUIDE
                )
        )
        guideRequestRepository.save(
                guideRequest.copy(
                        status = GuideRequestStatus.ACCEPTED,
                        message = changeGuideRequestStatus.message
                )
        )
        guideProfileRepository.save(
                GuideProfile(
                        user = guideRequest.user,
                        languages = guideRequest.languages,
                        experience = guideRequest.experience,
                        guideRequest = guideRequest
                )
        )

    }

    @Transactional
    fun rejectGuideRequest(id: Long, changeGuideRequestStatus: ChangeGuideRequestStatus) {
        val guideRequest = guideRequestRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Guide request not found.")
        guideRequestRepository.save(
                guideRequest.copy(
                        status = GuideRequestStatus.REJECTED,
                        message = changeGuideRequestStatus.message
                )
        )
    }

    fun addNewTag(newTag: NewTag) {
        tagRepository.save(
                Tag(
                        name = newTag.name
                )
        )
    }

    fun addNewInterest(newInterest: NewInterest) {
        interestRepository.save(
                Interest(
                        name = newInterest.name
                )
        )
    }

    @Transactional
    fun deleteTag(id: Long) {
        val tag = tagRepository.findByIdOrNull(id) ?: return
        offerRepository.saveAll(tag.offers.map { it.copy(tags = it.tags - tag) })
        tagRepository.delete(tag)
    }

    @Transactional
    fun deleteInterest(id: Long) {
        val interest = interestRepository.findByIdOrNull(id) ?: return
        userRepository.saveAll(interest.users.map { it.copy(interests = it.interests - interest) })
        interestRepository.delete(interest)
    }

    @Transactional
    fun deleteComplain(id: Long) {
        val complain = complainRepository.findByIdOrNull(id) ?: return
        complainRepository.delete(complain)
    }

    fun getAllComplains(): List<ComplainDto> {
        return complainRepository.findAll().asSequence().map { ComplainDto(it) }.toList()
    }

    @Transactional
    fun banUserById(id: Long, ban: Ban) {
        val user = userRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("User not found")
        userRepository.save(
                user.copy(
                        ban = ban.end
                )
        )
    }
}