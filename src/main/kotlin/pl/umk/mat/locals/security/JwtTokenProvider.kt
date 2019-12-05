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

    fun createToken(email: String, userId: Long): String {
        return JWT.create()
                .withSubject(email)
                .withClaim("userId", userId)
                .withClaim(lastTokenReleaseClaim, Date())
                .sign(Algorithm.HMAC512(config.secretKey))
    }

    fun createTokenForTemporaryUser(googleId: String, userId: Long): String {
        return JWT.create()
                .withSubject(googleId)
                .withClaim("userId", userId)
                .withClaim(lastTokenReleaseClaim, Date())
                .sign(Algorithm.HMAC512(config.secretKey))
    }

    fun getTokenPayload(token: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC512(config.secretKey))
                .build()
                .verify(token)
    }

    fun getTokenPayloadFromTokenForTemporaryUser(token: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC512(config.secretKey))
                .build()
                .verify(token)
    }

    fun getEmailFromTokenPayload(payload: DecodedJWT): String {
        return payload.subject
    }
}