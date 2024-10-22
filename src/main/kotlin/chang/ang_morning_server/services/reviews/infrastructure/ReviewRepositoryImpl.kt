package chang.ang_morning_server.services.reviews.infrastructure

import chang.ang_morning_server.services.reviews.domain.Review
import chang.ang_morning_server.services.reviews.domain.ReviewRepository

class ReviewRepositoryImpl(private val jpaRepository: ReviewJpaRepository) : ReviewRepository {
    override fun save(review: Review): Review {
        return this.jpaRepository.save(review)
    }
}