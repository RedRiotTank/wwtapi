package wwt.api.service

import exception.ResourceNotFoundExcepcion
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.modelmapper.ModelMapper

import wwt.api.entity.Item
import org.springframework.stereotype.Service
import wwt.api.dto.ItemInDto
import wwt.api.repository.ItemRepository
import wwt.api.utils.logger

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val modelMapper: ModelMapper
) {

    private val logger = logger()

        fun getItemById(id : Int): Item {
            return itemRepository.findById(id)
                .orElseThrow {
                    logger.error("Item $id not found")
                    ResourceNotFoundExcepcion("item", id) }
        }

    fun createItem(itemInDto: ItemInDto): Item {
        if (
            // !isValidMinecraftItem(itemInDto.itemId)  //TODO: manage item names
            true == false
            )
            throw IllegalArgumentException("Invalid Minecraft item ID")

        val existingItem = itemRepository.findByItemId(itemInDto.itemId)

        return existingItem ?: run {
            val newItem = modelMapper.map(itemInDto, Item::class.java)
            itemRepository.save(newItem)
        }
    }

    fun deleteItem(id: Int) {
        if (!itemRepository.existsById(id)) {
            logger.error("Item $id not found")
            throw ResourceNotFoundExcepcion("item", id)
        }

        itemRepository.deleteById(id)
    }


    fun isValidMinecraftItem(itemId: String): Boolean {
        val client = OkHttpClient()

        val url = "https://api.example.com/minecraft/items/$itemId"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response: Response ->
            return response.isSuccessful
        }
    }

}