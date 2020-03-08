package pl.umk.mat.locals.auth.utils

import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import pl.umk.mat.locals.user.UserService


@Service
class UserDetailsServiceImpl(
        @Lazy private val userService: UserService
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userService.findUserByEmail(username)?.let {
            UserPrincipal(it)
        } ?: throw UsernameNotFoundException(username)
    }
}