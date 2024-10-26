package chang.ang_morning_server.services.reviews.infrastructure

import chang.ang_morning_server.services.reviews.domain.Review
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ReviewJpaRepository : JpaRepository<Review, UUID> {
    fun findByUserIdAndHospitalId(userId: UUID, hospitalId: UUID): Review?
}