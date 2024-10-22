package chang.ang_morning_server.services.reviews.domain

import chang.ang_morning_server.common.ddd.AggregateRoot
import com.fasterxml.uuid.Generators
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity(name = "review")
class Review(
    @Column(nullable = false)
    val description: String,
    @Column(nullable = false)
    val hospitalId: UUID,
    @Id val id: UUID
) : AggregateRoot() {
    companion object {
        fun of(description: String, hospitalId: UUID): Review {
            return Review(description, hospitalId, Generators.timeBasedEpochGenerator().generate())
        }
    }
}