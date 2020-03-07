package pl.umk.mat.locals.user.interest

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InterestRepository : CrudRepository<Interest, Long>