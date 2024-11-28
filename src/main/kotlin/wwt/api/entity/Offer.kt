package wwt.api.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
@Schema(name = "Offer", description = "Offer entity")
data class Offer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "price", nullable = false)
    var price: Int,

    @Column(name = "count", nullable = false)
    var count: Int,

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "item", referencedColumnName = "id")
    var item: Item
)

