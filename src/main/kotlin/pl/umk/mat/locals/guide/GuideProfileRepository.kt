package pl.umk.mat.locals.guide

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GuideProfileRepository : CrudRepository<GuideProfile, Long>