package pl.umk.mat.locals.user.interest

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/interests")
@Api(tags = ["Interest Controller"], description = "This controller is used to manage available interests for user.")
class InterestController(
        private val interestService: InterestService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all available interests.")
    fun getAllInterest(): List<InterestDto> {
        return interestService.getAllInterests()
    }

    @PostMapping
    @ApiOperation("Add interest (Administrator only).", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addNewInterest(
            @RequestBody @Valid newInterest: NewInterest
    ) {
        interestService.addNewInterest(newInterest)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remove interest (Administrator only).", authorizations = [Authorization("JWT Token")])
    fun deleteInterest(
            @PathVariable id: Long
    ) {
        interestService.deleteInterest(id)
    }

}