package pl.umk.mat.locals.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.umk.mat.locals.dto.InterestDto
import pl.umk.mat.locals.services.InterestService

@RestController
@RequestMapping("/interests")
class InterestController(
        private val interestService: InterestService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllInterest(): List<InterestDto> {
        return interestService.getAllInterests()
    }

}