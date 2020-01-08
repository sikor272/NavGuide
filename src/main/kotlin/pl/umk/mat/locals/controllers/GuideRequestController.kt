package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.`in`.ChangeGuideRequestStatus
import pl.umk.mat.locals.dto.`in`.GuideRequestDto
import pl.umk.mat.locals.dto.out.AdministratorGuideRequest
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.AdministratorService
import pl.umk.mat.locals.services.UserService
import javax.validation.Valid


@RestController
@RequestMapping("/guiderequests")
@Api(tags = ["Guide Request Controller"], description = "This controller provide logic for administrators to menage guides.")
class GuideRequestController(
        private val administratorService: AdministratorService,
        private val userService: UserService
) {
    //ToDo
    @GetMapping
    @ApiOperation("Get all pending guide requests.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun getAllPendingGuideRequests(): List<AdministratorGuideRequest> {
        return administratorService.getAllPendingGuideRequests()
    }

    //ToDo
    @PutMapping("/{id}")
    @ApiOperation("Accept or reject guide request.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun acceptGuideRequest(
            @PathVariable id: Long,
            @RequestBody changeGuideRequestStatus: ChangeGuideRequestStatus
    ) {
        administratorService.changeGuideRequestStatus(id, changeGuideRequestStatus)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Add request to become a guide.", authorizations = [Authorization("JWT Token")])
    fun addRequestForGuide(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid guideRequest: GuideRequestDto
    ) {
        userService.addRequestForGuide(principal.user, guideRequest)
    }

}