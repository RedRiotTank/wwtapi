package wwt.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import wwt.api.embeddedId.UserEmbeddedId
import wwt.api.entity.User

@Repository
interface UserRepository : JpaRepository<User, UserEmbeddedId> {
}