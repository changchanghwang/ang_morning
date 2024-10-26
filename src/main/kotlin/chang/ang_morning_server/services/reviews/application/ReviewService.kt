package chang.ang_morning_server.services.reviews.application

import chang.ang_morning_server.services.reviews.command.WriteReviewCommand
import chang.ang_morning_server.services.reviews.domain.Review
import chang.ang_morning_server.services.reviews.domain.ReviewRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReviewService(private val reviewRepository: ReviewRepository) {
    fun write(userId: UUID, command: WriteReviewCommand) {
        //TODO: 중복 리뷰 검증
        val review = Review.of(
            command.description,
            command.score,
            command.hospitalId,
            userId
        )

        this.reviewRepository.save(review)
    }
}