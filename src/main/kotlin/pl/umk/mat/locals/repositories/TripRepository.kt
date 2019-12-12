package pl.umk.mat.locals.repositories

import org.springframework.data.geo.Distance
import org.springframework.data.geo.GeoResults
import org.springframework.data.geo.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.umk.mat.locals.models.Example
import pl.umk.mat.locals.models.Trip

@Repository
interface TripRepository: JpaRepository<Trip, Long>{
    @Query(value = "SELECT * FROM trip", nativeQuery = true)
    fun findAllTripsByPoint(lat: Float, lon: Float): List<Trip>

}