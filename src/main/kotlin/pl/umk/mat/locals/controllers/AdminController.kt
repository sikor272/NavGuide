package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.`in`.Ban
import pl.umk.mat.locals.dto.`in`.ChangeGuideRequestStatus
import pl.umk.mat.locals.dto.out.AdministratorGuideRequest
import pl.umk.mat.locals.services.AdministratorService


@RestController
@RequestMapping("/admin")
@Api(tags = ["Administration Controller"], description = "This controller provide logic for menage users in application.")
class AdminController(
        private val administratorService: AdministratorService
) {
    //ToDo
    @GetMapping("/guiderequests")
    @ApiOperation("Get all pending guide requests.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun getAllPendingGuideRequests(): List<AdministratorGuideRequest> {
        return administratorService.getAllPendingGuideRequests()
    }
    //ToDo
    @PostMapping("/guiderequests/{id}")
    @ApiOperation("Accept guide request.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun acceptGuideRequest(
            @PathVariable id: Long,
            @RequestBody changeGuideRequestStatus: ChangeGuideRequestStatus
    ) {
        administratorService.acceptGuideRequest(id, changeGuideRequestStatus)
    }
    //ToDo
    @DeleteMapping("/guiderequests/{id}")
    @ApiOperation("Reject guide request.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun rejectGuideRequest(
            @PathVariable id: Long,
            @RequestBody changeGuideRequestStatus: ChangeGuideRequestStatus
    ) {
        administratorService.rejectGuideRequest(id, changeGuideRequestStatus)
    }

    @PostMapping("/users/{id}/ban")
    @ApiOperation("Ban user", authorizations = [Authorization("JWT Token")])
    fun banUserById(
            @PathVariable id: Long,
            @RequestBody ban: Ban
    ) {
        administratorService.banUserById(id, ban)
    }
}