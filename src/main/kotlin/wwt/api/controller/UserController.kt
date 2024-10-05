package wwt.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import wwt.api.dto.UserInDto
import wwt.api.entity.User
import wwt.api.service.UserService
import java.util.UUID

@RestController
@RequestMapping("/wwtapi/users")
class UserController(
    private val userService: UserService
) {

    @Operation(
        summary = "Get a user by id",
        description = "Get a user by a given id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "User found"),
            ApiResponse(responseCode = "404", description = "User not found")
        ]
    )
    @GetMapping("/getUserById")
    fun getUserById(
        @Parameter(description = "The id of the player to be found", example = "---")
        @RequestParam playerUuid: UUID,
        @Parameter(description = "The id of the server to be found", example = "---")
        @RequestParam serverUuid: UUID
    ) : ResponseEntity<User> {
        val user = userService.getUserById(playerUuid, serverUuid)
        return ResponseEntity.ok(user)
    }
    @Operation(
        summary = "Create a user",
        description = "Create a user with the given data"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "User created"),
            ApiResponse(responseCode = "400", description = "Bad request")
        ]
    )
    @PostMapping("/createUser")
    fun createUser(
        @Parameter(description = "The data of the user to be created",
            example = """
                {
                    "playerUuid": "550e8400-e29b-41d4-a716-446655440000",
                    "serverUuid": "123e4567-e89b-12d3-a456-426614174000"
                }
            """
        )
        @RequestBody userInDto: UserInDto
    ) : ResponseEntity<User> {
        val user = userService.createUser(userInDto.playerUuid, userInDto.serverUuid)
        return ResponseEntity.ok(user)
    }

    @Operation(
        summary = "Delete a user",
        description = "Delete a user by a given id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "User deleted"),
            ApiResponse(responseCode = "404", description = "User not found")
        ]
    )
    @DeleteMapping("/deleteUser")
    fun deleteUser(
        @Parameter(description = "The id of the user to be deleted", example = "550e8400-e29b-41d4-a716-446655440000")
        @RequestParam playerUuid: UUID,
        @Parameter(description = "The id of the server to be deleted", example = "123e4567-e89b-12d3-a456-426614174000")
        @RequestParam serverUuid: UUID
    ) : ResponseEntity<Unit> {
        userService.deleteUser(playerUuid, serverUuid)
        return ResponseEntity.noContent().build()
    }
}