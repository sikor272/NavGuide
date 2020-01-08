package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.umk.mat.locals.dto.`in`.NewOffer
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.GuideService


@RestController
@RequestMapping("/offers")
@Api(tags = ["Offer Controller"], description = "This controller is used to manage offers.")
class OfferController(
        private val guideService: GuideService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new offer.", authorizations = [Authorization("JWT Token")])
    fun createNewOffer(
            @RequestParam file: List<MultipartFile>,
            @ModelAttribute offer: NewOffer,
            @AuthenticationPrincipal principal: UserPrincipal
    ) {
        guideService.addNewOffer(file, offer, principal.user)
    }
}