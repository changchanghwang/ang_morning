package chang.ang_morning_server.services.reviews.domain

interface ReviewRepository {
    fun save(review: Review): Review
}