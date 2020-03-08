package pl.umk.mat.locals.auth.utils

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import pl.umk.mat.locals.user.User
import java.util.*


class UserPrincipal(
        val user: User
) : UserDetails {

    override fun getAuthorities(): ArrayList<SimpleGrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority(user.role.name))
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return user.ban?.before(Date()) ?: true
    }
}