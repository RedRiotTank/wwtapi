package wwt.api.controller

import exception.ErrorResponse
import exception.ResourceNotFoundExcepcion
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import wwt.api.entity.Item
import wwt.api.service.ItemService

@RestController
@RequestMapping("/wwtapi/items")
class ItemController(
    private val itemService: ItemService
) {


    @GetMapping("/getItemById")
    fun getItemById(@RequestParam id : Int): ResponseEntity<Item> {
        val item = itemService.getItemById(id)
        return ResponseEntity.ok(item)
    }

    @ExceptionHandler(ResourceNotFoundExcepcion::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundExcepcion): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(HttpStatus.NOT_FOUND, ex.message?: "A resource was not found")

        return ResponseEntity.status(error.status).body(error)
    }
}