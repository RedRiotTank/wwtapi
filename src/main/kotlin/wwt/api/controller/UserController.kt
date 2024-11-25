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
        @Parameter(description = "The id of the user to be retrieved", example = "1")
        @RequestParam id: Int
    ) : ResponseEntity<User> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(user)
    }
    @Operation(
        summary = "Get a user by player UUID and server UUID",
        description = "Get a user by a given player UUID and server UUID"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "User found"),
            ApiResponse(responseCode = "404", description = "User not found")
        ]
    )
    @GetMapping("/getUserByPlayerUUIDAndServerUUID")
    fun getUserByPlayerUUIDAndServerUUID(
        @Parameter(description = "The player UUID of the user to be retrieved", example = "550e8400-e29b-41d4-a716-446655440000")
        @RequestParam playerUUID: UUID,
        @Parameter(description = "The server UUID of the user to be retrieved", example = "123e4567-e89b-12d3-a456-426614174000")
        @RequestParam serverUUID: UUID
    ) : ResponseEntity<User> {
        val user = userService.getUserByPlayerUUIDAndServerUUID(playerUUID, serverUUID)
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
        val user = userService.createUser(userInDto)
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
        @Parameter(description = "The id of the user to be retrieved", example = "1")
        @RequestParam id: Int
    ) : ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }
}