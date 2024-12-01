package wwt.api.service

import exception.ResourceNotFoundExcepcion
import org.modelmapper.ModelMapper
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import wwt.api.dto.OfferInDto
import wwt.api.entity.Offer
import wwt.api.repository.OfferRepository
import wwt.api.utils.logger
import java.util.*

@Service
class OfferService (
    private val offerRepository: OfferRepository,
    private val userService: UserService,
    private val itemService: ItemService,
    private val modelMapper: ModelMapper
){
    private val logger = logger()

    fun confirmOffer(offerId: Int, userId: Int): Boolean {
        val offer = offerRepository.findById(offerId)
            .orElseThrow {
                logger.error("Offer not found with id $offerId")
                ResourceNotFoundExcepcion("Offer not found with id $offerId", offerId) }

        val buyer = userService.getUserById(userId)
        val seller = offer.user

        if (buyer.money < offer.price)
            return false

        buyer.money -= offer.price
        seller.money += offer.price

        userService.updateUser(buyer)
        userService.updateUser(seller)

        offerRepository.delete(offer)

        logger.info("Offer confirmed: $offer")

        return true
    }
    fun getPaginatedOffers(pageNumber: Int): List<Offer> {
        val pageable: Pageable = PageRequest.of(pageNumber, 7)
        val offers: List<Offer> = offerRepository.findPaginatedOffers(pageable)

        return offers
    }

    fun createOffer(offerInDto: OfferInDto) : Offer {
        val offer :Offer = modelMapper.map(offerInDto, Offer::class.java)

        val user = userService.getUserById(offerInDto.user)
        offer.user = user

        val item = itemService.getItemById(offerInDto.item)
        offer.item = item

        offerRepository.save(offer)

        return offer
    }

}