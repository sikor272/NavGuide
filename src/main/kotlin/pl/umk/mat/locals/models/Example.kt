package pl.umk.mat.locals.models

import javax.persistence.*


@Entity
data class Example(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val exampleField: String
)