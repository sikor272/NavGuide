package pl.umk.mat.locals.utils

import org.springframework.data.domain.Persistable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long = -1
) : Persistable<Long> {

    override fun getId() = id

    override fun isNew() = id == -1L

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (id != (other as BaseEntity).id) return false
        return true
    }

    final override fun hashCode(): Int {
        return 31
    }

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
}
