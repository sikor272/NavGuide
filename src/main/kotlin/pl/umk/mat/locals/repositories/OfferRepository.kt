package pl.umk.mat.locals.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.Offer


@Repository
interface OfferRepository : CrudRepository<Offer, Long> {
    @Query(value = "SELECT * FROM offer WHERE (ST_Distance_Sphere( point(lon, lat), point(?2, ?1) ) / 1000) <= ?3",
            nativeQuery = true)
    fun findAllOffersByPoint(lat: Float, lon: Float, radius: Long): List<Offer>

}