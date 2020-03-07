package pl.umk.mat.locals.utils

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull
import pl.umk.mat.locals.utils.exceptions.ResourceNotFoundException

inline fun <reified T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    val formattedClassName = T::class.simpleName?.split(Regex("(?<=.)(?=(\\p{Upper}))"))?.joinToString(" ")
    return this.findByIdOrNull(id) ?: throw ResourceNotFoundException("$formattedClassName not found")
}