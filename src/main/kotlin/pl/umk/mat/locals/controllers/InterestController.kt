package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.In.NewInterest
import pl.umk.mat.locals.dto.Out.InterestDto
import pl.umk.mat.locals.services.AdministratorService
import pl.umk.mat.locals.services.InterestService

@RestController
@RequestMapping("/interests")
@Api(tags = ["Interest Controller"], description = "This controller is used to manage available interests for user.")
class InterestController(
        private val interestService: InterestService,
        private val administratorService: AdministratorService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all available interests.")
    fun getAllInterest(): List<InterestDto> {
        return interestService.getAllInterests()
    }

    @PostMapping
    @ApiOperation("Add interest.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addNewInterest(
            @RequestBody newInterest: NewInterest
    ) {
        administratorService.addNewInterest(newInterest)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove interest.", authorizations = [Authorization("JWT Token")])
    fun deleteInterest(
            @PathVariable id: Long
    ) {
        administratorService.deleteInterest(id)
    }
}