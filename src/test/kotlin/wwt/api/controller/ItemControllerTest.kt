package wwt.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import exception.ResourceNotFoundExcepcion
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.doNothing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import wwt.api.dto.ItemInDto
import wwt.api.entity.Item
import wwt.api.service.ItemService

@WebMvcTest(ItemController::class)
class ItemControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var itemService: ItemService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun getItemById() {
        // Given
        val id = 1
        val mockItem = Item(id = id, itemId = "minecraft:stone")

        // When
        given(itemService.getItemById(id)).willReturn(mockItem)

        mockMvc.perform(get("/wwtapi/items/getItemById")
            .param("id", id.toString()))

        // Then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.itemId").value("minecraft:stone"))
    }

    @Test
    fun getItemById_notFound() {
        // Given
        val id = -999

        // When
        given(itemService.getItemById(id)).willThrow(ResourceNotFoundExcepcion("id", id))

        mockMvc.perform(get("/wwtapi/items/getItemById")
            .param("id", id.toString()))

        // Then
            .andExpect(status().isNotFound)
    }

    @Test
    fun createItem() {
        // Given
        val itemInDto = ItemInDto(itemId = "minecraft:stone")
        val mockItem = Item(id = 1, itemId = "minecraft:stone")

        // When
        given(itemService.createItem(itemInDto)).willReturn(mockItem)

        mockMvc.perform(post("/wwtapi/items/createItem")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(itemInDto)))

        // Then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.itemId").value("minecraft:stone"))
    }

    @Test
    fun deleteItem() {
        // Given
        val itemId = 1

        // When
        doNothing().`when`(itemService).deleteItem(itemId)

        mockMvc.perform(delete("/wwtapi/items/deleteItem")
            .param("id", itemId.toString()))

        // Then
            .andExpect(status().isNoContent)
    }

    @Test
    fun deleteItem_notFound() {
        // Given
        val itemId = -1

        // When
        given(itemService.deleteItem(itemId)).willThrow(ResourceNotFoundExcepcion("Item", itemId))

        mockMvc.perform(delete("/wwtapi/items/deleteItem")
            .param("id", itemId.toString()))

        // Then
            .andExpect(status().isNotFound)
    }
}