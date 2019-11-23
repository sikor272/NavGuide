package pl.umk.mat.locals.services


import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.umk.mat.locals.dto.ExampleDto
import pl.umk.mat.locals.exceptions.ExampleNotFoundException
import pl.umk.mat.locals.repositories.ExampleRepository

@Service
class ExampleService(
        private val exampleRepository: ExampleRepository
) {

    fun getAllExamples(): List<ExampleDto> {
        return exampleRepository.findAll()
                .asSequence()
                .map {
                    ExampleDto(it)
                }.toList()
    }

    fun getExampleById(id: Long): ExampleDto {
        return exampleRepository.findByIdOrNull(id)?.let {
            ExampleDto(it)
        } ?: throw ExampleNotFoundException("Not found example.")
    }

}