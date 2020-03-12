package pl.umk.mat.locals.offer.purchase

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.auth.utils.UserPrincipal
import javax.validation.Valid

@RestController
@RequestMapping("/purchases")
@Api(tags = ["Purchase Controller"], description = "")
@Transactional
class PurchaseController(
        private val purchaseRequestService: PurchaseRequestService
) {

    @PostMapping
    @ApiOperation("Add purchase offer.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addPurchaseOffer(
            @RequestBody @Valid newPurchaseRequest: NewPurchaseRequest,
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        purchaseRequestService.addPurchaseOffer(newPurchaseRequest, principal.user)
    }

    @GetMapping
    @ApiOperation("Get purchase offers from self offers.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun getPurchaseOffersGuide(
            @AuthenticationPrincipal principal: UserPrincipal
    ): List<PurchaseRequestDto> {
        return purchaseRequestService.getPurchaseRequestsGuide(principal.user)
    }

    @PutMapping("/{id}")
    @ApiOperation("Accept or reject purchase offer.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun changePurchaseOfferStatus(
            @AuthenticationPrincipal principal: UserPrincipal,
            @PathVariable id: Long,
            @RequestBody @Valid changePurchaseOfferStatus: ChangePurchaseOfferStatus
    ) {
        purchaseRequestService.changePurchaseOfferStatus(id, principal.user, changePurchaseOfferStatus)
    }

}