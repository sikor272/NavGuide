package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.umk.mat.locals.dto.InterestDto
import pl.umk.mat.locals.services.InterestService

@RestController
@RequestMapping("/interests")
@Api(tags = ["Interest Controller"], description = "This controller is used to managed available interests for user.")
class InterestController(
        private val interestService: InterestService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all available interests.")
    fun getAllInterest(): List<InterestDto> {
        return interestService.getAllInterests()
    }

}