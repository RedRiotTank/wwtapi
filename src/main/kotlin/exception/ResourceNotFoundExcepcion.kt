package exception

import java.util.UUID

class ResourceNotFoundExcepcion(resource: String, id: Int) : RuntimeException(
    "Resource $resource with id: $id, not found"
)

class UserNotFoundException(
    playerUuuid: UUID,
    serverUuid: UUID) : RuntimeException(
    "Resource user with playerUuuid: $playerUuuid and serverUuid: $serverUuid, not found"
)
