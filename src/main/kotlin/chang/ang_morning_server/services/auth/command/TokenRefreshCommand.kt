package chang.ang_morning_server.services.auth.command

import jakarta.validation.constraints.NotBlank

data class TokenRefreshCommand(
    @field:NotBlank(message = "Token can't be empty")
    val refreshToken: String
)
