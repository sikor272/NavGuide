package pl.umk.mat.locals.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findUserByEmail(email: String): User?
    fun findUserByGoogleId(googleID: String): User?
    fun existsUserByEmail(email: String): Boolean
    fun existsUserByGoogleId(googleID: String): Boolean
}