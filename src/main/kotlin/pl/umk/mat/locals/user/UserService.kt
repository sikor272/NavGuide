package pl.umk.mat.locals.user

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.auth.NewUserData
import pl.umk.mat.locals.config.Config
import pl.umk.mat.locals.guide.request.GuideRequestRepository
import pl.umk.mat.locals.guide.request.SelfGuideRequest
import pl.umk.mat.locals.offer.bought.BoughtOfferDto
import pl.umk.mat.locals.offer.purchase.PurchaseRequestDto
import pl.umk.mat.locals.offer.purchase.PurchaseRequestRepository
import pl.umk.mat.locals.user.interest.InterestRepository
import pl.umk.mat.locals.utils.exceptions.BadRequest
import pl.umk.mat.locals.utils.findByIdOrThrow
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import javax.security.auth.message.AuthException
import javax.transaction.Transactional


@Service
class UserService(
        private val userRepository: UserRepository,
        private val interestRepository: InterestRepository,
        private val guideRequestRepository: GuideRequestRepository,
        private val config: Config,
        private val purchaseRequestRepository: PurchaseRequestRepository
) {

    fun findUserByEmail(email: String): User? {
        return userRepository.findUserByEmail(email)
    }

    fun getSelfHistoryOffer(user: User): List<BoughtOfferDto> {
        return user.boughtOffers.map { BoughtOfferDto(it) }
    }

    fun getSelfPurchaseRequests(user: User): List<PurchaseRequestDto> {
        return purchaseRequestRepository.getAllByTraveler(user).map {
            PurchaseRequestDto(it)
        }
    }

    fun findUserById(id: Long, questioningUser: User): UserDto {

        val user = userRepository.findByIdOrThrow(id)
        if (questioningUser.role == Role.ADMIN ||
                user.role == Role.GUIDE ||
                questioningUser.id == user.id ||
                (questioningUser.guideProfile != null &&
                        purchaseRequestRepository.existsByTravelerAndOffer_Owner(user, questioningUser.guideProfile)))
            return UserDto(user)
        throw AuthException("You don't have permission to display users!")
    }


    fun getSelfUserInfo(user: User): UserSelfInfo {
        return UserSelfInfo(user)
    }

    fun logoutFromAll(user: User) {
        userRepository.save(
                user.copy(
                        tokenUniqueId = kotlin.random.Random.nextInt(100000, 1000000000)
                )
        )
    }

    fun getAllGuideApplication(user: User): List<SelfGuideRequest> {
        return guideRequestRepository.getAllByUser(user).map {
            SelfGuideRequest(it)
        }
    }

    @Transactional
    fun setUserAvatar(file: MultipartFile, user: User) {
        val fileExtension = file.originalFilename?.substringAfterLast(".")?.toLowerCase()
                ?: throw BadRequest("Incorrect file extension.")
        if (fileExtension == file.originalFilename) throw BadRequest("Incorrect file extension.")
        if (!config.imageRegex.toRegex().matches(fileExtension)) throw BadRequest("Incorrect file type (only jpg, jpeg supported).")

        val filename = "avatar_${user.firstName.toLowerCase()}_${user.lastName.toLowerCase()}_${System.currentTimeMillis()}.$fileExtension"

        val filePatch = config.imageDir + filename
        if (File(filePatch).exists()) throw RuntimeException("File exist try later.")
        if (user.avatar.substringAfterLast("/") != "avatar_default.jpg")
            File(config.imageDir + user.avatar.substringAfterLast("/")).delete()

        userRepository.save(
                user.copy(
                        avatar = config.imageServerUrl + filename
                )
        )

        Files.copy(
                file.inputStream,
                Path.of(filePatch),
                StandardCopyOption.REPLACE_EXISTING
        )
    }

    @Transactional
    fun updateProfile(user: User, newUserData: NewUserData): UserSelfInfo {
        return UserSelfInfo(userRepository.save(
                user.copy(
                        email = newUserData.email,
                        firstName = newUserData.firstName,
                        lastName = newUserData.lastName,
                        country = newUserData.country,
                        telephone = newUserData.telephone,
                        experience = newUserData.experience,
                        interests = interestRepository.findAllById(newUserData.interests).asSequence().toList(),
                        gender = newUserData.gender,
                        age = newUserData.age
                ))
        )
    }

    fun setOneSignalId(user: User, oneSignalId: OneSignalId) {
        userRepository.save(
                user.copy(
                        oneSignalId = oneSignalId.oneSignalId
                )
        )
    }

}