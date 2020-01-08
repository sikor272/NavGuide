package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.`in`.Ban
import pl.umk.mat.locals.services.AdministratorService


@RestController
@RequestMapping("/admin")
@Api(tags = ["Administration Controller"], description = "This controller provide logic for menage users in application.")
class AdminController(
        private val administratorService: AdministratorService
) {

    @PostMapping("/users/{id}/ban")
    @ApiOperation("Ban user", authorizations = [Authorization("JWT Token")])
    fun banUserById(
            @PathVariable id: Long,
            @RequestBody ban: Ban
    ) {
        administratorService.banUserById(id, ban)
    }

}