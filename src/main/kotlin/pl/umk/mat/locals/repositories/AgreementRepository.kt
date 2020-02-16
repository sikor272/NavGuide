package pl.umk.mat.locals.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.Agreement
import pl.umk.mat.locals.models.GuideProfile
import pl.umk.mat.locals.models.User

@Repository
interface AgreementRepository : CrudRepository<Agreement, Long>{
    fun getAllByAuthorOrTarget(author: GuideProfile?, target: User): List<Agreement>
}