package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.*
import pl.umk.mat.locals.services.AdministratorService


@RestController
@RequestMapping("/admin")
@Api(tags = ["Administration Controller"], description = "This controller provide logic for menage users in application.")
class AdminController(
        private val administratorService: AdministratorService
) {
    @GetMapping("/guiderequests")
    @ApiOperation("Get all pending guide requests.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun getAllPendingGuideRequests(): List<AdministratorGuideRequest> {
        return administratorService.getAllPendingGuideRequests()
    }


    @PostMapping("/guiderequests/{id}")
    @ApiOperation("Accept guide request.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun acceptGuideRequest(
            @PathVariable id: Long,
            @RequestBody changeGuideRequestStatus: ChangeGuideRequestStatus
    ) {
        administratorService.acceptGuideRequest(id, changeGuideRequestStatus);
    }


    @DeleteMapping("/guiderequests/{id}")
    @ApiOperation("Reject guide request.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun rejectGuideRequest(
            @PathVariable id: Long,
            @RequestBody changeGuideRequestStatus: ChangeGuideRequestStatus
    ) {
        administratorService.rejectGuideRequest(id, changeGuideRequestStatus);
    }


    @PostMapping("/tags")
    @ApiOperation("Add tag (used in offers).", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addNewTag(
            @RequestBody newTag: NewTag
    ) {
        administratorService.addNewTag(newTag)
    }


    @DeleteMapping("/tags/{id}")
    @ApiOperation("Remove tag (used in offers).", authorizations = [Authorization("JWT Token")])
    fun deleteTag(
            @PathVariable id: Long
    ) {
        administratorService.deleteTag(id)
    }


    @PostMapping("/interests")
    @ApiOperation("Add interest.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addNewInterest(
            @RequestBody newInterest: NewInterest
    ) {
        administratorService.addNewInterest(newInterest)
    }


    @DeleteMapping("/interests/{id}")
    @ApiOperation("Remove interest.", authorizations = [Authorization("JWT Token")])
    fun deleteInterest(
            @PathVariable id: Long
    ) {
        administratorService.deleteInterest(id)
    }


    @GetMapping("/complains")
    @ApiOperation("Get all complains.", authorizations = [Authorization("JWT Token")])
    fun getAllComplains(): List<ComplainDto> {
        return administratorService.getAllComplains()
    }


    @DeleteMapping("/complains/{id}")
    @ApiOperation("Delete complain.", authorizations = [Authorization("JWT Token")])
    fun deleteComplain(
            @PathVariable id: Long
    ) {
        administratorService.deleteComplain(id)
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