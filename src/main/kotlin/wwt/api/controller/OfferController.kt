package wwt.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import wwt.api.dto.OfferConfirmInDto
import wwt.api.dto.OfferInDto
import wwt.api.entity.Offer
import wwt.api.service.OfferService
import wwt.api.utils.logger
import java.util.UUID

@RestController
@RequestMapping("/wwtapi/offers")
class OfferController(
    private val offerService: OfferService
) {

    private val logger = logger()

    @Operation(
        summary = "Get paginated offers",
        description = "Get a paginated list of offers"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Offers found"),
            ApiResponse(responseCode = "400", description = "Bad request")
        ]
    )
    @GetMapping("/getPaginatedOffers")
    fun getPaginatedOffers(
        @Parameter(description = "The page number to be retrieved", example = "0")
        @RequestParam page: Int
    ): ResponseEntity<List<Offer>> {
        logger.info("Requested paginated offers")
        offerService.getPaginatedOffers(page)

        return ResponseEntity.ok(offerService.getPaginatedOffers(page))
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
        logger.info("Requested create offer with data: $offerInDto")
        val createdOffer = offerService.createOffer(offerInDto)

        logger.info("Offer created: $createdOffer")
        return ResponseEntity.ok(createdOffer)
    }

    @Operation(
        summary = "Confirm an offer",
        description = "Confirm an offer with the given data"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Offer confirmed"),
            ApiResponse(responseCode = "400", description = "Bad request")
        ]
    )
    @PostMapping("/confirmOffer")
    fun confirmOffer(
        @Parameter(description = "The id of the offer to be confirmed", example = "1")
        @RequestBody offerConfirmInDto: OfferConfirmInDto
    ): ResponseEntity<Boolean> {
        logger.info("Requested confirm offer with data: ${offerConfirmInDto.offerId}, ${offerConfirmInDto.userId}")
        val result = offerService.confirmOffer(offerConfirmInDto.offerId, offerConfirmInDto.userId)

        return ResponseEntity.ok(result)
    }
}