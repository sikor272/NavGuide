package pl.umk.mat.locals.user.interest

import pl.umk.mat.locals.user.User
import javax.persistence.*

@Entity
data class Interest(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String,

        @ManyToMany(mappedBy = "interests", fetch = FetchType.LAZY)
        val users: List<User> = emptyList()

)