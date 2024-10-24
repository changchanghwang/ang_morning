package chang.ang_morning_server.services.auth.command

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
