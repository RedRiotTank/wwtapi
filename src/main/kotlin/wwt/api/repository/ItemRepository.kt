package wwt.api.repository

import wwt.api.entity.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ItemRepository : JpaRepository<Item, Int> {
    fun findByItemId(itemId: String): Item?
}