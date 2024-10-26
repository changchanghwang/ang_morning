package chang.ang_morning_server.services.reviews.infrastructure

import chang.ang_morning_server.services.reviews.domain.Review
import chang.ang_morning_server.services.reviews.domain.ReviewRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ReviewRepositoryImpl(private val jpaRepository: ReviewJpaRepository) : ReviewRepository {
    override fun save(review: Review): Review {
        return this.jpaRepository.save(review)
    }

    override fun findByUserIdAndHospitalId(userId: UUID, hospitalId: UUID): Review? {
        return this.jpaRepository.findByUserIdAndHospitalId(userId, hospitalId)
    }
}