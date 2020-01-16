package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.`in`.NewComplain
import pl.umk.mat.locals.dto.out.ComplainDto
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.AdministratorService
import pl.umk.mat.locals.services.OfferService
import javax.validation.Valid


@RestController
@RequestMapping("/complains")
@Api(tags = ["Complain Controller"], description = "This controller is used to manage complaints for offers.")
class ComplainController(
        private val administratorService: AdministratorService,
        private val offerService: OfferService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all complains.", authorizations = [Authorization("JWT Token")])
    fun getAllComplains(): List<ComplainDto> {
        return administratorService.getAllComplains()
    }

    @PostMapping
    @ApiOperation("Add new complains.", authorizations = [Authorization("JWT Token")])
    fun addNewComplains(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid newComplain: NewComplain
    ) {
        offerService.addNewComplain(newComplain, principal.user)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete complain.", authorizations = [Authorization("JWT Token")])
    fun deleteComplain(
            @PathVariable id: Long
    ) {
        administratorService.deleteComplain(id)
    }

}