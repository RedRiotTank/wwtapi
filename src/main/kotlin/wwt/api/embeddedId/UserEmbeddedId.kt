package wwt.api.embeddedId

import jakarta.persistence.Embeddable
import wwt.api.utils.toByteArray
import java.io.Serializable
import java.util.UUID

@Embeddable
data class UserEmbeddedId(
    var playerUuid: ByteArray,
    var serverUuid: ByteArray
) : Serializable {

    constructor(playerUuid: UUID, serverUuid: UUID) : this(playerUuid.toByteArray(), serverUuid.toByteArray())
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEmbeddedId

        if (!playerUuid.contentEquals(other.playerUuid)) return false
        if (!serverUuid.contentEquals(other.serverUuid)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = playerUuid.contentHashCode()
        result = 31 * result + serverUuid.contentHashCode()
        return result
    }
}