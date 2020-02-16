package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.GuideRequest
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.models.enumerations.Status

@Repository
interface GuideRequestRepository : CrudRepository<GuideRequest, Long> {
    fun getAllByUser(user: User): List<GuideRequest>
    fun getAllByStatus(status: Status): List<GuideRequest>
    fun existsByUserAndStatus(user: User, status: Status): Boolean
}