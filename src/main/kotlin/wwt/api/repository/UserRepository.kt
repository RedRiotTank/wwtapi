package wwt.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import wwt.api.entity.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Int> {

    @Query("SELECT u FROM User u WHERE u.playerUUID = :playerUUID AND u.serverUUID = :serverUUID")
    fun findUserByPlayerUUIDAndServerUUID(playerUUID: UUID, serverUUID: UUID): Optional<User>
}