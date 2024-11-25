package wwt.api.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.jetbrains.annotations.NotNull

@Schema(name = "offerInDto", description = "Offer input data", requiredProperties = ["itemId"])
data class OfferInDto(
    @NotNull
    @field:Schema(description = "The price of the offer", example = "100", required = true)
    val price: Int,

    @NotNull
    @field:Schema(description = "The count of the offer", example = "10", required = true)
    val count: Int,

    @NotNull
    @field:Schema(description = "The user of the offer", example = "1", required = true)
    val user: Int,

    @NotNull
    @field:Schema(description = "The id of the item to be found", example = "1", required = true)
    val item: Int
)
