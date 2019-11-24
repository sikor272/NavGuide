package pl.umk.mat.locals.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
class UserAuthException(
        message: String
) : RuntimeException(message)