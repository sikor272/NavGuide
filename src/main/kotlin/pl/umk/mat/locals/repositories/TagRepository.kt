package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.Tag


@Repository
interface TagRepository : CrudRepository<Tag, Long> {
}