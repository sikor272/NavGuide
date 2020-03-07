package pl.umk.mat.locals.offer.agreement

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.guide.GuideProfile
import pl.umk.mat.locals.user.User

@Repository
interface AgreementRepository : CrudRepository<Agreement, Long> {
    fun getAllByOfferOwnerOrTraveler(offerOwner: GuideProfile?, traveler: User): List<Agreement>
}