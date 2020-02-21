package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.PurchaseRequest
import pl.umk.mat.locals.models.User

@Repository
interface PurchaseRequestRepository : CrudRepository<PurchaseRequest, Long> {
    fun getAllByTraveler(traveler: User): List<PurchaseRequest>
    fun existsByTravelerAndUserGuide(traveler: User, userGuide: User): Boolean
}