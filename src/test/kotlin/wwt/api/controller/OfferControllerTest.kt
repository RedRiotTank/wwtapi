package wwt.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import wwt.api.dto.OfferInDto
import wwt.api.entity.Item
import wwt.api.entity.Offer
import wwt.api.entity.User
import wwt.api.service.OfferService
import java.util.*
import kotlin.test.Test

@WebMvcTest(OfferControllerTest::class)
class OfferControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var offerService: OfferService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    //@Test
    //TODO: fix this test
    fun createOffer() {
        // Given
        val offerInDto = OfferInDto(
            price = 100,
            count = 1,
            user = 1,
            item = 1
        )

        val mockOffer = Offer(
            id = 1,
            price = 100,
            count = 1,
            user = User(id = 1, playerUUID = UUID.randomUUID(), serverUUID = UUID.randomUUID(), money = 0),
            item = Item(id = 1, itemId = "minecraft:stone")
        )

        // When
        given(offerService.createOffer(offerInDto)).willReturn(mockOffer)

        mockMvc.perform(post("/wwtapi/offers/createOffer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(offerInDto)))

        // Then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.price").value(100))
    }
}