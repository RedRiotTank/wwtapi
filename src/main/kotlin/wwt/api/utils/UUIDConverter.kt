package wwt.api.utils

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.nio.ByteBuffer
import java.util.*

@Converter(autoApply = true)
class UUIDConverter : AttributeConverter<UUID, ByteArray> {

    override fun convertToDatabaseColumn(uuid: UUID?): ByteArray? {
        if (uuid == null) return null
        val byteBuffer = ByteBuffer.wrap(ByteArray(16))
        byteBuffer.putLong(uuid.mostSignificantBits)
        byteBuffer.putLong(uuid.leastSignificantBits)
        return byteBuffer.array()
    }

    override fun convertToEntityAttribute(dbData: ByteArray?): UUID? {
        if (dbData == null || dbData.size != 16) return null
        val byteBuffer = ByteBuffer.wrap(dbData)
        val mostSignificantBits = byteBuffer.long
        val leastSignificantBits = byteBuffer.long
        return UUID(mostSignificantBits, leastSignificantBits)
    }
}