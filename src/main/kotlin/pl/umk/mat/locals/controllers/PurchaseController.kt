package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.`in`.ChangeGuideRequestStatus
import pl.umk.mat.locals.dto.`in`.ChangePurchaseOfferStatus
import pl.umk.mat.locals.dto.`in`.NewPurchaseRequest
import pl.umk.mat.locals.dto.out.PurchaseRequestDto
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.OfferService
import javax.validation.Valid

@RestController
@RequestMapping("/purchases")
@Api(tags = ["Purchase Controller"], description = "")
class PurchaseController(
        private val offerService: OfferService
) {

    @PostMapping
    @ApiOperation("Add purchase offer.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addPurchaseOffer(
            @RequestBody @Valid newPurchaseRequest: NewPurchaseRequest,
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        offerService.addPurchaseOffer(newPurchaseRequest, principal.user)
    }

    @GetMapping
    @ApiOperation("Get purchase offers from self offers.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun getPurchaseOffersGuide(
            @AuthenticationPrincipal principal: UserPrincipal
    ): List<PurchaseRequestDto> {
        return offerService.getPurchaseRequestsGuide(principal.user)
    }

    @PutMapping("/{id}")
    @ApiOperation("Accept or reject purchase offer.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun changePurchaseOfferStatus(
            @AuthenticationPrincipal principal: UserPrincipal,
            @PathVariable id: Long,
            @RequestBody @Valid changePurchaseOfferStatus: ChangePurchaseOfferStatus
    ) {
        offerService.changePurchaseOfferStatus(id, principal.user, changePurchaseOfferStatus)
    }

}