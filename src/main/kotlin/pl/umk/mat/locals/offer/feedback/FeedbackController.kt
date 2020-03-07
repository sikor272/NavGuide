package pl.umk.mat.locals.offer.feedback


import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.umk.mat.locals.auth.utils.UserPrincipal
import javax.validation.Valid


@RestController
@RequestMapping("/feedback")
@Api(tags = ["Feedback Controller"], description = "This controller is used to feedback.")
class FeedbackController(
        private val feedbackService: FeedbackService
) {

    @PostMapping
    @ApiOperation("Add new feedback.", authorizations = [Authorization("JWT Token")])
    fun addNewFeedback(
            @AuthenticationPrincipal principal: UserPrincipal,
            @RequestBody @Valid newFeedback: NewFeedback
    ) {
        feedbackService.addFeedback(newFeedback, principal.user)
    }

}