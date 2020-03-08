package pl.umk.mat.locals.auth.utils

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails

class TokenBasedAuthentication(
        private val principle: UserDetails,
        private val token: String
) : AbstractAuthenticationToken(principle.authorities) {

    override fun getCredentials(): String {
        return token
    }

    override fun getPrincipal(): UserDetails {
        return principle
    }

    override fun isAuthenticated(): Boolean {
        return true
    }
}