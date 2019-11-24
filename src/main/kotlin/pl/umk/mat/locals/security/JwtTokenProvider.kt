package pl.umk.mat.locals.security



import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.stereotype.Component
import pl.umk.mat.locals.config.Config
import pl.umk.mat.locals.exceptions.UserAuthException

import java.util.*

@Component
class JwtTokenProvider(
        private val config: Config
) {
    private final val lastTokenReleaseClaim = "lastTokenRelease"

    fun createToken(username: String, userId: Long, lastTokenRelease: Date): String {
        return JWT.create()
                .withSubject(username)
                .withClaim("userId", userId)
                .withClaim(lastTokenReleaseClaim, lastTokenRelease)
                .sign(Algorithm.HMAC512(config.secretKey))
    }

    fun getTokenPayload(token: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC512(config.secretKey))
                .build()
                .verify(token)
    }

    fun getUsernameFromTokenPayload(payload: DecodedJWT): String {
        return payload.subject
    }
}