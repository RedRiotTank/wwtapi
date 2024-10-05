package wwt.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.jetbrains.annotations.NotNull
import java.util.UUID

@Schema(name = "UserInDto", description = "User input data", requiredProperties = ["playerUuid", "serverUuid"])
data class UserInDto(
    @NotNull
    @field:Schema(description = "The id of the player to be found", example = "550e8400-e29b-41d4-a716-44665544000", required = true)
    val playerUuid: UUID,

    @NotNull
    @field:Schema(description = "The id of the server to be found", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
    val serverUuid: UUID
)