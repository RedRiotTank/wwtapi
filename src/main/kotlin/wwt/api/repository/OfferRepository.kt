package wwt.api.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import wwt.api.entity.Offer

@Repository
interface OfferRepository : JpaRepository<Offer, Int> {

    @Query("SELECT o FROM Offer o")
    fun findPaginatedOffers(pag: Pageable): List<Offer>
}