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

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val modelMapper: ModelMapper
) {
        fun getItemById(id : Int): Item {
            return itemRepository.findById(id)
                .orElseThrow() { ResourceNotFoundExcepcion("item", id) }
        }

    fun createItem(itemInDto: ItemInDto): Item {
        if (
            // !isValidMinecraftItem(itemInDto.itemId)  //TODO: manage item names
            true == false
            )
            throw IllegalArgumentException("Invalid Minecraft item ID")

        val item :Item = modelMapper.map(itemInDto, Item::class.java)
        itemRepository.save(item)

        return item
    }

    fun deleteItem(id: Int) {
        if (!itemRepository.existsById(id)) {
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