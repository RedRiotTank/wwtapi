package wwt.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import wwt.api.entity.User
import wwt.api.service.UserService
import java.util.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import wwt.api.dto.UserInDto

@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun getUserById() {
        // Given
        val id = 1
        val mockUser = User(
            id = id,
            playerUUID = UUID.randomUUID(),
            serverUUID = UUID.randomUUID(),
            money = 300
        )

        // When
        given(userService.getUserById(id)).willReturn(mockUser)

        mockMvc.perform(get("/wwtapi/users/getUserById")
            .param("id", id.toString()))

        // Then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(id))
    }

    @Test
    fun getUserByPlayerUUIDAndServerUUID() {
        // Given
        val playerUUID = UUID.randomUUID()
        val serverUUID = UUID.randomUUID()
        val mockUser = User(
            id = 1,
            playerUUID = playerUUID,
            serverUUID = serverUUID,
            money = 300
        )

        // When
        given(userService.getUserByPlayerUUIDAndServerUUID(playerUUID, serverUUID)).willReturn(mockUser)

        mockMvc.perform(get("/wwtapi/users/getUserByPlayerUUIDAndServerUUID")
            .param("playerUUID", playerUUID.toString())
            .param("serverUUID", serverUUID.toString()))

        // Then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.playerUUID").value(playerUUID.toString()))
            .andExpect(jsonPath("$.serverUUID").value(serverUUID.toString()))
    }

    @Test
    fun createUser() {
        // Given
        val userInDto = UserInDto(
            playerUuid = UUID.randomUUID(),
            serverUuid = UUID.randomUUID()
        )

        val mockUser = User(
            id = 1,
            playerUUID = userInDto.playerUuid,
            serverUUID = userInDto.serverUuid,
            money = 0
        )

        // When
        given(userService.createUser(userInDto)).willReturn(mockUser)

        mockMvc.perform(post("/wwtapi/users/createUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userInDto)))

        // Then
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.playerUUID").value(userInDto.playerUuid.toString()))
            .andExpect(jsonPath("$.serverUUID").value(userInDto.serverUuid.toString()))
    }
}