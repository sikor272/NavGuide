package pl.umk.mat.locals.auth.admin

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/admin")
@Api(tags = ["Administration Controller"], description = "This controller provide logic for menage users in application.")
class AdminController(
        private val administratorService: AdministratorService
) {

    @PostMapping("/users/{id}/ban")
    @ApiOperation("Ban user (Administrator only)", authorizations = [Authorization("JWT Token")])
    fun banUserById(
            @PathVariable id: Long,
            @RequestBody @Valid ban: Ban
    ) {
        administratorService.banUserById(id, ban)
    }

}