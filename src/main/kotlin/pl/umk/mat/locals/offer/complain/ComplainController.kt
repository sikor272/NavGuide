package pl.umk.mat.locals.offer.complain

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.auth.utils.UserPrincipal
import javax.validation.Valid


@RestController
@RequestMapping("/complains")
@Api(tags = ["Complain Controller"], description = "This controller is used to manage complaints for offers.")
class ComplainController(
        private val complainService: ComplainService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all complains (Administrator only).", authorizations = [Authorization("JWT Token")])
    fun getAllComplains(): List<ComplainDto> {
        return complainService.getAllComplains()
    }

    @PostMapping
    @ApiOperation("Add new complains.", authorizations = [Authorization("JWT Token")])
    fun addNewComplains(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid newComplain: NewComplain
    ) {
        complainService.addNewComplain(newComplain, principal.user)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete complain (Administrator only).", authorizations = [Authorization("JWT Token")])
    fun deleteComplain(
            @PathVariable id: Long
    ) {
        complainService.deleteComplain(id)
    }

}