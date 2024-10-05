package wwt.api.service

import exception.UserNotFoundException
import org.springframework.stereotype.Service
import wwt.api.embeddedId.UserEmbeddedId
import wwt.api.entity.User
import wwt.api.repository.UserRepository

import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getUserById(playerUuid: UUID, serverUuid: UUID): User {
        return userRepository.findById(UserEmbeddedId(playerUuid,serverUuid))
            .orElseThrow() { UserNotFoundException(playerUuid, serverUuid) }
    }

    fun createUser(playerUuid: UUID, serverUuid: UUID): User {
        userRepository.findById(UserEmbeddedId(playerUuid,serverUuid))
            .ifPresent { throw IllegalArgumentException("User already exists") }

        val user = User(playerUuid, serverUuid, 0)
        userRepository.save(user)
        return user
    }

    fun deleteUser(playerUuid: UUID, serverUuid: UUID) {
        userRepository.findById(UserEmbeddedId(playerUuid,serverUuid))
            .orElseThrow() { UserNotFoundException(playerUuid, serverUuid) }

        userRepository.deleteById(UserEmbeddedId(playerUuid,serverUuid))
    }
}