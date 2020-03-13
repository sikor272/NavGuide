package pl.umk.mat.locals.offer.bought

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User

@Repository
interface BoughtOfferRepository : CrudRepository<BoughtOffer, Long> {
    fun findAllByOfferOwner(guide: GuideProfile): List<BoughtOffer>
    fun existsByTravelerAndOffer(traveler: User, offer: Offer): Boolean
    fun findAllByTraveler(user: User): List<BoughtOffer>
    fun findAllByOffer(offer: Offer): List<BoughtOffer>
}