package pl.umk.mat.locals.message

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.auth.utils.UserPrincipal
import javax.validation.Valid


@RestController
@RequestMapping("/messages")
@Api(tags = ["Message Controller"], description = "This controller is used to manage available message.")
class MessageController(
        private val messageService: MessageService
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get all available messages.", authorizations = [Authorization("JWT Token")])
    fun getAllNotificationByUser(
            @AuthenticationPrincipal principal: UserPrincipal,
            @PathVariable id: Long
    ): List<MessageDto> {
        return messageService.getAllMessagesForPurchaseRequest(principal.user, id)
    }


    @PostMapping("/{id}")
    @ApiOperation("Add message to purchaseRequest", authorizations = [Authorization("JWT Token")])
    @ResponseStatus(HttpStatus.OK)
    fun addNewNotification(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid newMessage: NewMessage,
            @PathVariable id: Long
    ) {
        messageService.addNewMessage(principal.user,newMessage,id)
    }

}