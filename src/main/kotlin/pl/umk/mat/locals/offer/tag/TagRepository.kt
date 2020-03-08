package pl.umk.mat.locals.offer.tag

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface TagRepository : CrudRepository<Tag, Long>