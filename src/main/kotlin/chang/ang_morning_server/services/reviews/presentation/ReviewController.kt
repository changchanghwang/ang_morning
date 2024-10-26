package chang.ang_morning_server.services.reviews.presentation

import chang.ang_morning_server.common.security.UserPrincipal
import chang.ang_morning_server.services.reviews.application.ReviewService
import chang.ang_morning_server.services.reviews.command.WriteReviewCommand
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/reviews")
class ReviewController(private val reviewService: ReviewService) {

    @PostMapping
    fun writeReview(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody command: WriteReviewCommand
    ) {
        this.reviewService.write(UUID.fromString(userPrincipal.username), command)
    }
}