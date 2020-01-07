package pl.umk.mat.locals.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.config.Config
import pl.umk.mat.locals.dto.GuideProfileDto
import pl.umk.mat.locals.dto.NewOffer
import pl.umk.mat.locals.dto.OfferDto
import pl.umk.mat.locals.exceptions.BadRequest
import pl.umk.mat.locals.exceptions.ResourceNotFoundException
import pl.umk.mat.locals.models.Offer
import pl.umk.mat.locals.models.User
import pl.umk.mat.locals.repositories.GuideProfileRepository
import pl.umk.mat.locals.repositories.OfferRepository
import pl.umk.mat.locals.repositories.TagRepository
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import javax.transaction.Transactional

@Service
class GuideService(
        private val tagRepository: TagRepository,
        private val offerRepository: OfferRepository,
        private val config: Config,
        private val guideProfileRepository: GuideProfileRepository
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
                    Path.of(config.imageServerUrl + patch),
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
                            tagRepository.findByIdOrNull(it) ?: throw ResourceNotFoundException("Tags of id list do not exist")
                        },
                        photos = filename
                )
        )
    }

    fun getGuideProfile(id: Long): GuideProfileDto {
        return GuideProfileDto(guideProfileRepository.findByIdOrNull(id)
                ?: throw ResourceNotFoundException("Guide not found"))
    }

    fun getGuideOffer(id: Long): List<OfferDto> {
        return guideProfileRepository.findByIdOrNull(id)?.offers?.map { OfferDto(it) }
                ?: throw ResourceNotFoundException("Guide not found")
    }
}