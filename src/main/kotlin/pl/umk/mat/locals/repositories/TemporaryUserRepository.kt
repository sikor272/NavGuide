package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import pl.umk.mat.locals.models.TemporaryUser

interface TemporaryUserRepository : CrudRepository<TemporaryUser, Long>
