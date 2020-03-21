package pl.umk.mat.locals.offer.bought

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User

@Repository
interface BoughtOfferRepository : CrudRepository<BoughtOffer, Long> {
    fun existsByTravelerAndOffer(traveler: User, offer: Offer): Boolean
}