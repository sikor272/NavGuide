package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.User

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findUserByEmail(email: String): User?
    fun findUserByGoogleId(googleID: String): User?
    fun existsUserByEmail(email: String): Boolean
    fun existsUserByGoogleId(googleID: String): Boolean
}