package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/admin")
@Api(tags = ["Administration Controller"], description = "This controller provide logic for menage users in application.")
class AdminController {


}