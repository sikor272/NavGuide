package pl.umk.mat.locals.auth.admin


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.umk.mat.locals.user.UserRepository
import pl.umk.mat.locals.utils.findByIdOrThrow

@Service
class AdministratorService(
        private val userRepository: UserRepository
) {

    @Transactional
    fun banUserById(id: Long, ban: Ban) {
        val user = userRepository.findByIdOrThrow(id)
        userRepository.save(
                user.copy(
                        ban = ban.end
                )
        )
    }
}