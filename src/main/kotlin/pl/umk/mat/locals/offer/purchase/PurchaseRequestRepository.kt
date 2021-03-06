package pl.umk.mat.locals.offer.purchase

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.user.User

@Repository
interface PurchaseRequestRepository : CrudRepository<PurchaseRequest, Long> {
    fun getAllByTraveler(traveler: User): List<PurchaseRequest>
    fun existsByTravelerAndOffer_Owner(traveler: User, userGuide: GuideProfile): Boolean
}