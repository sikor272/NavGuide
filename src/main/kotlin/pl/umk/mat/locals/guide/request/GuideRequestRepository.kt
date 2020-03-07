package pl.umk.mat.locals.guide.request

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.enumerations.Status

@Repository
interface GuideRequestRepository : CrudRepository<GuideRequest, Long> {
    fun getAllByUser(user: User): List<GuideRequest>
    fun getAllByStatus(status: Status): List<GuideRequest>
    fun existsByUserAndStatus(user: User, status: Status): Boolean
}