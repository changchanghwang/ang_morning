package chang.ang_morning_server.services.reviews.command

import java.util.*

data class WriteReviewCommand(val description: String, val score: Int, val hospitalId: UUID)
