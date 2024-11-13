package wwt.api.utils

import java.nio.ByteBuffer
import java.util.*

fun UUID.toByteArray(): ByteArray {
    val byteBuffer = ByteBuffer.wrap(ByteArray(16))
    byteBuffer.putLong(this.mostSignificantBits)
    byteBuffer.putLong(this.leastSignificantBits)
    return byteBuffer.array()
}

fun ByteArray.toUUID(): UUID {
    val byteBuffer = ByteBuffer.wrap(this)
    val mostSigBits = byteBuffer.long
    val leastSigBits = byteBuffer.long
    return UUID(mostSigBits, leastSigBits)
}