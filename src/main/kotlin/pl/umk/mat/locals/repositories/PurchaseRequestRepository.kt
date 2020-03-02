package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.GuideProfile
import pl.umk.mat.locals.models.PurchaseRequest
import pl.umk.mat.locals.models.User

@Repository
interface PurchaseRequestRepository : CrudRepository<PurchaseRequest, Long> {
    fun getAllByTraveler(traveler: User): List<PurchaseRequest>
    fun existsByTravelerAndOffer_Owner(traveler: User, userGuide: GuideProfile): Boolean
}