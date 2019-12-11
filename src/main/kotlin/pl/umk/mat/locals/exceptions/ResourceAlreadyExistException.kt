package pl.umk.mat.locals.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT)
class ResourceAlreadyExistException(
        message: String
) : RuntimeException(message)