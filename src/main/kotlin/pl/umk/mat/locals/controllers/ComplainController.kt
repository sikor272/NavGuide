package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.In.NewComplain
import pl.umk.mat.locals.dto.Out.ComplainDto
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.AdministratorService
import pl.umk.mat.locals.services.UserService


@RestController
@RequestMapping("/complains")
@Api(tags = ["Complain Controller"], description = "This controller is used to manage complaints for offers.")
class ComplainController(
        private val administratorService: AdministratorService,
        private val userService: UserService
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
            @RequestBody newComplain: NewComplain
    ) {
        userService.addNewComplain(newComplain, principal.user)
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete complain.", authorizations = [Authorization("JWT Token")])
    fun deleteComplain(
            @PathVariable id: Long
    ) {
        administratorService.deleteComplain(id)
    }
}