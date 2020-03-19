package pl.umk.mat.locals.offer.complain

import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.BaseEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class Complain(


        @ManyToOne(fetch = FetchType.LAZY)
        val author: User,

        val description: String,

        @ManyToOne(fetch = FetchType.LAZY)
        val target: Offer

) : BaseEntity()