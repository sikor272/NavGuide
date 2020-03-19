package pl.umk.mat.locals.offer.tag

import pl.umk.mat.locals.offer.Offer
import pl.umk.mat.locals.utils.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany


@Entity
data class Tag(


        @Column(nullable = false, unique = true, length = 32)
        val name: String,

        @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
        val offers: List<Offer> = emptyList()
) : BaseEntity()