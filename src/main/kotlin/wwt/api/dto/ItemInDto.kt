package wwt.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.jetbrains.annotations.NotNull

@Schema(name = "ItemInDto", description = "Item input data", requiredProperties = ["itemId"])
data class ItemInDto(

    @NotNull
    @field:Schema(description = "Item ID in minecraft", example = "minecraft:stone", required = true)
    val itemId: String
)