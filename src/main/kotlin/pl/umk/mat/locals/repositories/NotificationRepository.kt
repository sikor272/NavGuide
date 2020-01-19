package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.Notification
import pl.umk.mat.locals.models.User


@Repository
interface NotificationRepository : CrudRepository<Notification, Long> {
    fun findAllByUser(user: User): List<Notification>
}