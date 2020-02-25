package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.`in`.ChangeAgreementStatus
import pl.umk.mat.locals.dto.`in`.NewAgreement
import pl.umk.mat.locals.dto.out.AgreementDto
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.AgreementService
import javax.validation.Valid


@RestController
@RequestMapping("/agreements")
@Api(tags = ["Agreement Controller"], description = "This controller is used to agreements.")
class AgreementController(
        private val agreementService: AgreementService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new agreement.", authorizations = [Authorization("JWT Token")])
    fun createNewOffer(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid newAgreement: NewAgreement
    ) {
        agreementService.createNewAgreement(principal.user, newAgreement)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all own agreements.", authorizations = [Authorization("JWT Token")])
    fun getOwnAgreements(
            @AuthenticationPrincipal principal: UserPrincipal
    ): List<AgreementDto> {
        return agreementService.getOwnAgreements(principal.user)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Change agreement status.", authorizations = [Authorization("JWT Token")])
    fun changeAgreementStatus(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid changeAgreementStatus: ChangeAgreementStatus,
            @PathVariable id: Long
    ) {
        agreementService.changeAgreementStatus(id, principal.user, changeAgreementStatus)
    }

}