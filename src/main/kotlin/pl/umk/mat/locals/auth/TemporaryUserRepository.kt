package pl.umk.mat.locals.auth

import org.springframework.data.repository.CrudRepository

interface TemporaryUserRepository : CrudRepository<TemporaryUser, Long> {
    fun findTemporaryUserByGoogleId(googleID: String): TemporaryUser?
}
