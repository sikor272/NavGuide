package pl.umk.mat.locals.notofication

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.user.User


@Repository
interface NotificationRepository : CrudRepository<Notification, Long> {
    fun findAllByUser(user: User): List<Notification>
}