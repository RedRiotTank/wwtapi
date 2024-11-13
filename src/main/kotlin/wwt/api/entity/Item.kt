package wwt.api.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull

@Entity
@Schema(name = "Item", description = "Item entity")
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Item ID", example = "1")
    var id: Int,

    @NotNull
    @field:Schema(description = "Item ID in minecraft", example = "minecraft:stone")
    var itemId: String
)
