package wwt.api.service

import exception.ResourceNotFoundExcepcion
import okhttp3.OkHttpClient
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.modelmapper.ModelMapper
import wwt.api.dto.ItemInDto
import wwt.api.entity.Item
import wwt.api.repository.ItemRepository
import java.util.*

class ItemServiceTest {

    @Mock
    private lateinit var itemRepository: ItemRepository

    @Mock
    private lateinit var modelMapper: ModelMapper

    @InjectMocks
    private lateinit var itemService: ItemService

    init {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getItemById should return item when found`() {
        // Given
        val id = 1
        val mockItem = Item(id = id, itemId = "minecraft:stone")
        given(itemRepository.findById(id)).willReturn(Optional.of(mockItem))

        // When
        val result = itemService.getItemById(id)

        // Then
        assertNotNull(result)
        assertEquals(mockItem, result)
    }

    @Test
    fun `getItemById should throw ResourceNotFoundExcepcion when not found`() {
        // Given
        val id = -1
        given(itemRepository.findById(id)).willReturn(Optional.empty())

        // When / Then
        val exception = assertThrows<ResourceNotFoundExcepcion> {
            itemService.getItemById(id)
        }
    }

    @Test
    fun `createItem should create and return item`() {
        // Given
        val itemInDto = ItemInDto(itemId = "minecraft:stone")
        val mockItem = Item(id = 1, itemId = "minecraft:stone")

        // When
        given(modelMapper.map(itemInDto, Item::class.java)).willReturn(mockItem)
        given(itemRepository.save(mockItem)).willReturn(mockItem)

        val result = itemService.createItem(itemInDto)

        // Then
        assertNotNull(result)
        assertEquals(mockItem, result)
        verify(itemRepository).save(mockItem)
    }


    //TODO: test when itemId is invalid
    /*
    @Test
    fun `createItem should throw IllegalArgumentException for invalid item id`() {
        // Given
        val itemInDto = ItemInDto(itemId = "invalid:item")

        // When / Then
        assertThrows<IllegalArgumentException> {
            itemService.createItem(itemInDto)
        }
    }
     */

    @Test
    fun `deleteItem should delete item when found`() {
        // Given
        val itemId = 1
        given(itemRepository.existsById(itemId)).willReturn(true)
        doNothing().`when`(itemRepository).deleteById(itemId)

        // When
        itemService.deleteItem(itemId)

        // Then
        verify(itemRepository).deleteById(itemId)
    }

    @Test
    fun `deleteItem should throw ResourceNotFoundExcepcion when item not found`() {
        // Given
        val itemId = -1
        given(itemRepository.existsById(itemId)).willReturn(false)

        // When / Then
        assertThrows<ResourceNotFoundExcepcion> {
            itemService.deleteItem(itemId)
        }

    }
}
