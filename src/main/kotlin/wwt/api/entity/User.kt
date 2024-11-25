package wwt.api.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import wwt.api.utils.UUIDConverter
import java.util.*

@Entity
@Schema(name = "User", description = "User entity. It represents a user in a server")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "User identification")
    var id : Int,

    @Column(name = "Player_UUID")
    @Convert(converter = UUIDConverter::class)
    @NotNull
    @Schema(description = "Player identification")
    var playerUUID: UUID,

    @Column(name = "Server_UUID")
    @Convert(converter = UUIDConverter::class)
    @NotNull
    @Schema(description = "Server identification")
    var serverUUID: UUID,

    @Schema(description = "Money the user have in a server", example = "300")
    var money: Int
)


