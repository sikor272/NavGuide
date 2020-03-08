package pl.umk.mat.locals.user.interest


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.findByIdOrThrow

@Service
class InterestService(
        private val interestRepository: InterestRepository,
        private val userRepository: UserRepository
) {

    fun getAllInterests(): List<InterestDto> {
        return interestRepository.findAll()
                .asSequence()
                .map {
                    InterestDto(it)
                }.toList()
    }


    fun addNewInterest(newInterest: NewInterest) {
        interestRepository.save(
                Interest(
                        name = newInterest.name
                )
        )
    }


    @Transactional
    fun deleteInterest(id: Long) {
        val interest = interestRepository.findByIdOrThrow(id)
        userRepository.saveAll(interest.users.map { it.copy(interests = it.interests - interest) })
        interestRepository.delete(interest)
    }
}