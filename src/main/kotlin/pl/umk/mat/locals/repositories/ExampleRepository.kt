package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.Example


@Repository
interface ExampleRepository : CrudRepository<Example, Long> {
    fun findAllByExampleField(string: String): List<Example>
}