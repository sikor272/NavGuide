package pl.umk.mat.locals.offer


import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.config.Config
import pl.umk.mat.locals.offer.tag.TagRepository
import pl.umk.mat.locals.user.User
import pl.umk.mat.locals.utils.exceptions.BadRequest
import pl.umk.mat.locals.utils.findByIdOrThrow
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import javax.transaction.Transactional

@Service

class OfferService(
        private val offerRepository: OfferRepository,
        private val tagRepository: TagRepository,
        private val config: Config
) {

    @Transactional
    fun addNewOffer(file: List<MultipartFile>, offer: NewOffer, user: User) {
        val availableLetters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123654789"
        val filename = file.map { currentFile ->
            val extension = currentFile.originalFilename?.substringAfterLast(".")?.toLowerCase()
                    ?: throw BadRequest("Incorrect file extension.")
            if (extension == currentFile.originalFilename) throw BadRequest("Incorrect file extension.")

            if (!config.imageRegex.toRegex().matches(extension))
                throw BadRequest("Incorrect file type (only jpg, jpeg, png supported).")

            var patch: String
            var counter = 0
            do {
                counter += 1
                patch = "offer_" + (1..30).map {
                    kotlin.random.Random.nextInt(0, availableLetters.length)
                }.map {
                    availableLetters[it]
                }.joinToString("") + ".$extension"
            } while (File(config.imageDir + patch).exists() && counter < 10)
            if (File(config.imageDir + patch).exists())
                throw RuntimeException("Can not create new name to file.")
            Files.copy(
                    currentFile.inputStream,
                    Path.of(config.imageDir + patch),
                    StandardCopyOption.REPLACE_EXISTING
            )
            config.imageServerUrl + patch
        }

        offerRepository.save(
                Offer(
                        name = offer.name,
                        city = offer.city,
                        lat = offer.lat,
                        lon = offer.lon,
                        radius = offer.radius,
                        begin = offer.begin,
                        end = offer.end,
                        maxPeople = offer.maxPeople,
                        price = offer.price,
                        priceType = offer.priceType,
                        owner = user.guideProfile ?: throw RuntimeException("Cannot find owner profile"),
                        tags = offer.tags.map {
                            tagRepository.findByIdOrThrow(it)
                        },
                        photos = filename,
                        description = offer.description
                )
        )
    }


    fun getAllOffersByGeoLocalization(lat: Double, lon: Double, radius: Long): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findAllOffersByPoint(lat, lon, radius).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }

    fun getAllOffersByCity(city: String): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findAllOffersByCity(city).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }


    fun getOfferById(id: Long): OfferDto {
        return OfferDto(offerRepository.findByIdOrThrow(id))
    }

    fun getAllOffersByName(name: String): List<OfferDto> {
        return offerRepository.saveAll(offerRepository.findAllByNameContaining(name).map {
            it.copy(
                    inSearch = it.inSearch + 1
            )
        }).asSequence().map {
            OfferDto(it)
        }.toList()
    }

}
