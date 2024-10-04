package wwt.api.service

import exception.ResourceNotFoundExcepcion
import wwt.api.entity.Item
import org.springframework.stereotype.Service
import wwt.api.repository.ItemRepository

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {
        fun getItemById(id : Int): Item {
            return itemRepository.findById(id)
                .orElseThrow() { ResourceNotFoundExcepcion("item", id) }
        }
}