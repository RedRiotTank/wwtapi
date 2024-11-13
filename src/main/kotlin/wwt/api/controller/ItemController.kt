package wwt.api.controller

import exception.ErrorResponse
import exception.ResourceNotFoundExcepcion
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import wwt.api.dto.ItemInDto
import wwt.api.entity.Item
import wwt.api.service.ItemService

@RestController
@RequestMapping("/wwtapi/items")
class ItemController(
    private val itemService: ItemService
) {

    @Operation(
        summary = "Get an item by id",
        description = "Get an item by a given id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Item found",
                content = [Content(schema = Schema(implementation = Item::class))]),
            ApiResponse(responseCode = "404", description = "Item not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @GetMapping("/getItemById")
    fun getItemById(
        @Parameter(description = "The id of the item to be found", example = "1")
        @RequestParam id : Int
    ): ResponseEntity<Item> {
        val item = itemService.getItemById(id)
        return ResponseEntity.ok(item)
    }

    @Operation(
        summary = "Create an item",
        description = "Create an item with the given data"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Item created",
                content = [Content(schema = Schema(implementation = Item::class))]),
            ApiResponse(responseCode = "400", description = "Bad request")
        ])
    @PostMapping("/createItem")
    fun createItem(
        @Parameter(description = "The data of the item to be created")
        @RequestBody itemInDto: ItemInDto
    ): ResponseEntity<Item> {
        val createdItem = itemService.createItem(itemInDto)
        return ResponseEntity.ok(createdItem)
    }

    @Operation(
        summary = "Delete an item",
        description = "Delete an item by a given id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Item deleted"),
            ApiResponse(responseCode = "404", description = "Item not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @DeleteMapping("/deleteItem")
    fun deleteItem(
        @Parameter(description = "The id of the item to be deleted", example = "1")
        @RequestParam id: Int
    ): ResponseEntity<Unit> {
        itemService.deleteItem(id)
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler(ResourceNotFoundExcepcion::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundExcepcion): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(HttpStatus.NOT_FOUND, ex.message?: "A resource was not found")

        return ResponseEntity.status(error.status).body(error)
    }
}