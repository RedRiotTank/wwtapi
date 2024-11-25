package wwt.api.service

import exception.UserNotFoundException
import exception.UserUUIDsNotFoundException
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import wwt.api.dto.UserInDto
import wwt.api.entity.User
import wwt.api.repository.UserRepository
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val modelMapper: ModelMapper
) {

    fun getUserById(id: Int): User {
        return userRepository.findById(id)
            .orElseThrow() { UserNotFoundException(id) }
    }

    fun getUserByPlayerUUIDAndServerUUID(playerUUID: UUID, serverUUID: UUID): User {
        return userRepository.findUserByPlayerUUIDAndServerUUID(playerUUID, serverUUID)
            .orElseThrow() { UserUUIDsNotFoundException(playerUUID, serverUUID) }
    }

    fun createUser(userInDto: UserInDto): User {
        userRepository.findUserByPlayerUUIDAndServerUUID(userInDto.playerUuid, userInDto.serverUuid)
            .ifPresent { throw IllegalArgumentException("User already exists") }

        val user = modelMapper.map(userInDto, User::class.java)
        userRepository.save(user)
        return user
    }

    fun deleteUser(id: Int) {
        userRepository.findById(id)
            .orElseThrow() { UserNotFoundException(id) }

        userRepository.deleteById(id)
    }
}