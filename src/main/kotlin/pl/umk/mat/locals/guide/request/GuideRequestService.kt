package pl.umk.mat.locals.guide.request


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.guide.GuideProfileRepository
import pl.umk.mat.locals.user.Role
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.enumerations.ChangeStatus
import pl.umk.mat.locals.utils.enumerations.Status
import pl.umk.mat.locals.utils.exceptions.ResourceAlreadyExistException
import pl.umk.mat.locals.utils.findByIdOrThrow

@Service
class GuideRequestService(
        private val guideRequestRepository: GuideRequestRepository,
        private val userRepository: UserRepository,
        private val guideProfileRepository: GuideProfileRepository
) {

    @Transactional
    fun addRequestForGuide(user: User, guideRequest: GuideRequestDto) {
        if (guideRequestRepository.existsByUserAndStatus(user, Status.PENDING)) throw ResourceAlreadyExistException("You already have pending request.")
        guideRequestRepository.save(GuideRequest(
                user = user,
                languages = guideRequest.languages,
                experience = guideRequest.experience,
                description = guideRequest.description
        ))
    }

    fun getAllPendingGuideRequests(): List<AdministratorGuideRequest> {
        return guideRequestRepository.getAllByStatus(Status.PENDING).map {
            AdministratorGuideRequest(it)
        }
    }


    fun acceptGuideRequest(user: User, id: Long, changeGuideRequestStatus: ChangeGuideRequestStatus) {
        val guideRequest = guideRequestRepository.findByIdOrThrow(id)

        userRepository.save(
                guideRequest.user.copy(
                        role = Role.GUIDE,
                        guideProfile = guideProfileRepository.save(
                                GuideProfile(
                                        user = guideRequest.user,
                                        languages = guideRequest.languages.map { it },
                                        experience = guideRequest.experience,
                                        guideRequest = guideRequest
                                )
                        )
                )
        )
        guideRequestRepository.save(
                guideRequest.copy(
                        status = Status.ACCEPTED,
                        message = changeGuideRequestStatus.message,
                        processedBy = user
                )
        )

    }


    fun rejectGuideRequest(user: User, id: Long, changeGuideRequestStatus: ChangeGuideRequestStatus) {
        val guideRequest = guideRequestRepository.findByIdOrThrow(id)
        guideRequestRepository.save(
                guideRequest.copy(
                        status = Status.REJECTED,
                        message = changeGuideRequestStatus.message,
                        processedBy = user
                )
        )
    }


    @Transactional
    fun changeGuideRequestStatus(user: User, id: Long, changeGuideRequestStatus: ChangeGuideRequestStatus) {
        if (changeGuideRequestStatus.guideRequestStatus == ChangeStatus.ACCEPT) {
            acceptGuideRequest(user, id, changeGuideRequestStatus)
        } else {
            rejectGuideRequest(user, id, changeGuideRequestStatus)
        }
    }
}