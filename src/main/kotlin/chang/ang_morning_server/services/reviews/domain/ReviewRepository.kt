package chang.ang_morning_server.services.reviews.domain

import java.util.*

interface ReviewRepository {
    fun save(review: Review): Review
    fun findByUserIdAndHospitalId(userId: UUID, hospitalId: UUID): Review?
}