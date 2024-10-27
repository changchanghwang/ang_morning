package chang.ang_morning_server.services.reviews.domain

import chang.ang_morning_server.common.ddd.AggregateRoot
import chang.ang_morning_server.services.reviews.domain.services.ReviewValidator
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
    @Column(nullable = false)
    val score: Int,
    @Column(nullable = false)
    val userId: UUID,
    @Id val id: UUID
) : AggregateRoot() {
    companion object {
        fun of(
            description: String,
            score: Int,
            hospitalId: UUID,
            userId: UUID,
            reviewValidator: ReviewValidator
        ): Review {
            reviewValidator.validateWriting(userId, hospitalId)
            return Review(description, hospitalId, score, userId, Generators.timeBasedEpochGenerator().generate())
        }
    }
}