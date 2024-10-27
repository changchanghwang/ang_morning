package chang.ang_morning_server.services.reviews.application

import chang.ang_morning_server.services.reviews.command.WriteReviewCommand
import chang.ang_morning_server.services.reviews.domain.Review
import chang.ang_morning_server.services.reviews.domain.ReviewRepository
import chang.ang_morning_server.services.reviews.domain.services.ReviewValidator
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val reviewValidator: ReviewValidator
) {
    fun write(userId: UUID, command: WriteReviewCommand) {
        val review = Review.of(
            command.description,
            command.score,
            command.hospitalId,
            userId,
            reviewValidator
        )

        this.reviewRepository.save(review)
    }
}