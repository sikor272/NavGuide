package pl.umk.mat.locals.guide.request

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.auth.utils.UserPrincipal
import javax.validation.Valid

@RestController
@RequestMapping("/guiderequests")
@Api(tags = ["Guide Request Controller"], description = "This controller provide logic for administrators to menage guides.")
class GuideRequestController(
        private val guideRequestService: GuideRequestService
) {

    @GetMapping
    @ApiOperation("Get all pending guide requests (Administrator only).", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun getAllPendingGuideRequests(): List<AdministratorGuideRequest> {
        return guideRequestService.getAllPendingGuideRequests()
    }

    @PutMapping("/{id}")
    @ApiOperation("Accept or reject guide request (Administrator only).", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun acceptGuideRequest(
            @AuthenticationPrincipal principal: UserPrincipal,
            @PathVariable id: Long,
            @RequestBody @Valid changeGuideRequestStatus: ChangeGuideRequestStatus
    ) {
        guideRequestService.changeGuideRequestStatus(principal.user, id, changeGuideRequestStatus)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Add request to become a guide.", authorizations = [Authorization("JWT Token")])
    fun addRequestForGuide(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid guideRequest: GuideRequestDto
    ) {
        guideRequestService.addRequestForGuide(principal.user, guideRequest)
    }

}