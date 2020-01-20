package pl.umk.mat.locals.services


import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.out.NotificationDto
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.models.enumerations.NotificationStatus
import pl.umk.mat.locals.repositories.NotificationRepository

@Service
class NotificationService(
        private val notificationRepository: NotificationRepository
) {

    fun getAllNotificationByUser(user: User): List<NotificationDto> {
        return notificationRepository.findAllByUser(user)
                .asSequence()
                .map {
                    NotificationDto(it)
                }.toList()
    }

    fun updateNotification(id: Long) {
        val notification = notificationRepository.findByIdOrNull(id)
                ?: throw ResourceNotFoundException("Notification not found.")
        notificationRepository.save(
                notification.copy(
                        status = NotificationStatus.READ
                )
        )
    }
}