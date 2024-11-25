package exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

class ResourceNotFoundExcepcion(resource: String, id: Int) : RuntimeException(
    "Resource $resource with id: $id, not found"
)

class UserNotFoundException(
    id: Int
) : RuntimeException(
    "Resource user with id: $id, not found"
)

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserUUIDsNotFoundException(
    playerUUID: UUID,
    serverUUID: UUID
) : RuntimeException(
    "Resource user with playerUUID: $playerUUID and serverUUID: $serverUUID, not found"
)