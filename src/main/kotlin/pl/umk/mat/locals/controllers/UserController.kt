package pl.umk.mat.locals.controllers

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.umk.mat.locals.dto.out.UserDto
import pl.umk.mat.locals.services.UserService


@RestController
@RequestMapping("/users")
@Api(tags = ["User Controller"], description = "This controller provides logic for authenticated users to manage his account.")
class UserController(
        private val userService: UserService
) {
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get User by ID ", authorizations = [Authorization("JWT Token")])
    fun getOfferById(
            @PathVariable id: Long
    ): UserDto {
        return UserDto(userService.findUserById(id))
    }
}