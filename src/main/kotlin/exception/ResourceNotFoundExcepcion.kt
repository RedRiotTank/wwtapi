package exception

class ResourceNotFoundExcepcion(resource: String, id: Int) : RuntimeException(
    "Resource $resource with id: $id, not found"
)

