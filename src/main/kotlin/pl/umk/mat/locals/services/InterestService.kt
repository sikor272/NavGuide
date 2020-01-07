package pl.umk.mat.locals.services

import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.Out.InterestDto
import pl.umk.mat.locals.repositories.InterestRepository

@Service
class InterestService(
        private val interestRepository: InterestRepository
) {

    fun getAllInterests(): List<InterestDto> {
        return interestRepository.findAll()
                .asSequence()
                .map {
                    InterestDto(it)
                }.toList()
    }

}