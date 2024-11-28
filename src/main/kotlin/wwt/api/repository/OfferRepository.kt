package wwt.api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import wwt.api.entity.Offer

@Repository
interface OfferRepository : JpaRepository<Offer, Int> {
}