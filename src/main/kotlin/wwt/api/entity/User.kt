package wwt.api.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import wwt.api.embeddedId.UserEmbeddedId
import java.util.*

@Entity
@Schema(name = "User", description = "User entity. It represents a user in a server")
data class User(
    @EmbeddedId
    @Schema(description = "User identification with player and server UUID")
    var userId : UserEmbeddedId,

    @Schema(description = "Money the user have in a server", example = "300")
    var money: Int
) {

    constructor(
        playerUuid: UUID,
        serverUuid: UUID,
        money: Int
    ) : this(UserEmbeddedId(playerUuid,serverUuid),money)
}