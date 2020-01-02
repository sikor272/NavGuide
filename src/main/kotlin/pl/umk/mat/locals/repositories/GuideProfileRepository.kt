package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.GuideProfile

@Repository
interface GuideProfileRepository : CrudRepository<GuideProfile, Long>