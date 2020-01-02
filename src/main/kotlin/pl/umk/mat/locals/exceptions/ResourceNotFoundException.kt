package pl.umk.mat.locals.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.CONFLICT)
class ResourceNotFoundException(
        message: String
) : RuntimeException(message)