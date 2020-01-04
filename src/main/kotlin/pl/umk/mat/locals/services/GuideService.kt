package pl.umk.mat.locals.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.config.Config
import pl.umk.mat.locals.dto.NewOffer
import pl.umk.mat.locals.exceptions.BadRequest
import pl.umk.mat.locals.models.Offer
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.TagRepository
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import javax.transaction.Transactional

@Service
class GuideService(
        private val tagRepository: TagRepository,
        private val offerRepository: OfferRepository,
        private val config: Config
) {
    @Transactional
    fun addNewOffer(file: List<MultipartFile>, offer: NewOffer, user: User) {
        var index: Long = 0
        val filename = file.map {
            val extension = it.originalFilename?.substringAfterLast(".")?.toLowerCase()
                    ?: throw BadRequest("Incorrect file extension.")
            if (extension == it.originalFilename) throw BadRequest("Incorrect file extension.")

            if (!"jpg|jpeg|png".toRegex().matches(extension))
                throw BadRequest("Incorrect file type (only jpg, jpeg, png supported).")
            val availableLetters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123654789"
            var patch: String
            do {
                patch = "offer_" + (1..30).map {
                    kotlin.random.Random.nextInt(0, availableLetters.length)
                }.map {
                    availableLetters[it]
                }.joinToString("") + ".$extension"
            } while (File(config.imageDir + patch).exists())
            Files.copy(
                    it.inputStream,
                    Path.of(config.imageDir + patch)
                    //StandardCopyOption.REPLACE_EXISTING
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
                        tags = offer.tags.map { tagRepository.findByIdOrNull(it) ?: throw RuntimeException("") },
                        photos = filename
                )
        )
    }

}