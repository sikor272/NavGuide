package pl.umk.mat.locals.guide

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.user.User

@Repository
interface GuideProfileRepository : CrudRepository<GuideProfile, Long> {
    fun findByGuideRequestUser(user: User): GuideProfile?
}