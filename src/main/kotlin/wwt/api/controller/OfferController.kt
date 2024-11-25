package wwt.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wwt.api.dto.OfferInDto
import wwt.api.entity.Offer
import wwt.api.service.OfferService

@RestController
@RequestMapping("/wwtapi/offers")
class OfferController(
    private val offerService: OfferService
) {

    fun getOfferById() {
        // TODO
    }

    @Operation(
        summary = "Create an offer",
        description = "Create an offer with the given data"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Offer created"),
            ApiResponse(responseCode = "400", description = "Bad request")
        ]
    )
    @PostMapping("/createOffer")
    fun createOffer(
        @Parameter(description = "The data of the offer to be created")
        @RequestBody offerInDto: OfferInDto
    ): ResponseEntity<Offer> {
        val createdOffer = offerService.createOffer(offerInDto)
        return ResponseEntity.ok(createdOffer)
    }
}