package pl.umk.mat.locals.offer

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface OfferRepository : CrudRepository<Offer, Long> {
    @Query(value = "SELECT * FROM offer WHERE (ST_Distance_Sphere( point(lon, lat), point(?2, ?1) )) <= ?3 + radius",
            nativeQuery = true)
    fun findAllOffersByPoint(lat: Double, lon: Double, radius: Long): List<Offer>

    @Query(value = "SELECT * FROM offer ORDER BY ((ST_Distance_Sphere( point(lon, lat), point(?2, ?1) )) - radius) LIMIT ?3",
            nativeQuery = true)
    fun findAllOffersNearByPoint(lat: Double, lon: Double, count: Int): List<Offer>

    fun findAllOffersByCity(city: String): List<Offer>

    @Query(value = "SELECT * FROM offer ORDER BY RAND() LIMIT 10",
            nativeQuery = true)
    fun findRandomOffers(): List<Offer>

    fun findAllByNameContaining(name: String): List<Offer>

}