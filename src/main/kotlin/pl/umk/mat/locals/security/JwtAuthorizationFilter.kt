package pl.umk.mat.locals.security

import com.auth0.jwt.exceptions.TokenExpiredException

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import pl.umk.mat.locals.exceptions.UserAuthException
import pl.umk.mat.locals.services.UserDetailsServiceImpl

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthorizationFilter(
        private val jwtTokenProvider: JwtTokenProvider,
        private val userDetailsService: UserDetailsServiceImpl
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val token = getTokenFromRequest(request)

        if (token != null) {
            val tokenPayload = try {
                jwtTokenProvider.getTokenPayload(token)
            } catch (e: TokenExpiredException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired.")
                return
            } catch (e: Exception) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Something is wrong with Token: $e.")
                return
            }

            val username = jwtTokenProvider.getUsernameFromTokenPayload(tokenPayload)

            val userDetails = try {
                userDetailsService.loadUserByUsername(username)
            } catch (e: UsernameNotFoundException) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found.")
                return
            } as UserPrincipal

            val authentication = TokenBasedAuthentication(userDetails, token)
            SecurityContextHolder.getContext().authentication = authentication

        }

        chain.doFilter(request, response)
    }


    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization") ?: return null

        return if (!header.startsWith("Bearer ")) {
            null
        } else {
            header.removePrefix("Bearer ")
        }
    }


}