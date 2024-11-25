package wwt.api.service

import exception.UserNotFoundException
import org.mockito.BDDMockito.given
import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.modelmapper.ModelMapper
import wwt.api.entity.User
import wwt.api.repository.UserRepository
import java.util.*
import kotlin.test.Test

class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var modelMapper: ModelMapper

    @InjectMocks
    private lateinit var userService: UserService

    init {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getUserById should return user when found`() {
        // Given
        val id = 1
        val mockUser = User(
            id = id,
            playerUUID = UUID.randomUUID(),
            serverUUID = UUID.randomUUID(),
            money = 300
        )
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser))

        // When
        val result = userService.getUserById(id)

        // Then
        assertNotNull(result)
        assertEquals(mockUser, result)
    }

}