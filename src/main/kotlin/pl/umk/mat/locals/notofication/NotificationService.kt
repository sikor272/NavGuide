package pl.umk.mat.locals.notofication


import org.springframework.stereotype.Service
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.findByIdOrThrow
import java.util.*

@Service
class NotificationService(
        private val notificationRepository: NotificationRepository,
        private val userRepository: UserRepository
) {

    fun getAllNotificationByUser(user: User): List<NotificationDto> {
        return notificationRepository.findAllByUser(user)
                .asSequence()
                .map {
                    NotificationDto(it)
                }.toList()
    }

    fun updateNotification(id: Long) {
        val notification = notificationRepository.findByIdOrThrow(id)
        notificationRepository.save(
                notification.copy(
                        status = NotificationStatus.READ
                )
        )
    }

    fun addNewNotification(newNotification: NewNotification) {
        notificationRepository.save(
                Notification(
                        name = newNotification.name,
                        description = newNotification.description,
                        date = Date(),
                        user = userRepository.findByIdOrThrow(newNotification.user)
                )
        )
    }
}