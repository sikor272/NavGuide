package pl.umk.mat.locals.user

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.user.interest.Interest

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findUserByEmail(email: String): User?
    fun findUserByGoogleId(googleID: String): User?
    fun existsUserByEmail(email: String): Boolean
    fun existsUserByGoogleId(googleID: String): Boolean
    fun findAllByInterestsIn(interest: Interest): List<User>
}