package pl.umk.mat.locals.models


import org.springframework.data.geo.Point
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Trip(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val name: String,
        val lat: Float,
        val lon: Float
)