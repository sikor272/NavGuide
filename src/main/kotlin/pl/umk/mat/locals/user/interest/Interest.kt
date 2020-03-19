package pl.umk.mat.locals.user.interest

import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.BaseEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany

@Entity
data class Interest(
        val name: String,

        @ManyToMany(mappedBy = "interests", fetch = FetchType.LAZY)
        val users: List<User> = emptyList()

) : BaseEntity()