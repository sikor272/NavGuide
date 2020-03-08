package pl.umk.mat.locals.offer.complain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ComplainRepository : CrudRepository<Complain, Long>