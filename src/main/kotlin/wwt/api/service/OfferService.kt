package wwt.api.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import wwt.api.dto.OfferInDto
import wwt.api.entity.Offer
import wwt.api.repository.OfferRepository

@Service
class OfferService (
    private val offerRepository: OfferRepository,
    private val userService: UserService,
    private val itemService: ItemService,
    private val modelMapper: ModelMapper
){

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