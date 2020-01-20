package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.`in`.NewNotification
import pl.umk.mat.locals.dto.out.NotificationDto
import pl.umk.mat.locals.security.UserPrincipal
import pl.umk.mat.locals.services.AdministratorService
import pl.umk.mat.locals.services.NotificationService
import javax.validation.Valid


@RestController
@RequestMapping("/notifications")
@Api(tags = ["Notification Controller"], description = "This controller is used to manage available notifications.")
class NotificationController(
        private val notificationService: NotificationService,
        private val administratorService: AdministratorService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all available notifications.", authorizations = [Authorization("JWT Token")])
    fun getAllNotificationByUser(
            @AuthenticationPrincipal principal: UserPrincipal
    ): List<NotificationDto> {
        return notificationService.getAllNotificationByUser(principal.user)
    }

    @PutMapping("/{id}")
    @ApiOperation("Change notification status.", authorizations = [Authorization("JWT Token")])
    fun updateNotification(
            @PathVariable id: Long
    ) {
        notificationService.updateNotification(id)
    }

    @PostMapping
    @ApiOperation("Add notification for user.", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addNewNotification(
            @RequestBody @Valid newNotification: NewNotification
    ) {
        administratorService.addNewNotification(newNotification)
    }


}