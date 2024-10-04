package wwt.api.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import wwt.api.dto.ItemDto
import wwt.api.entity.Item

@Mapper
interface ItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itemId", source = "itemId")
    fun toEntity(dto: ItemDto): Item

    @Mapping(target = "id", source = "id")
    @Mapping(target = "itemId", source = "itemId")
    fun toDto(entity: Item): ItemDto
}