package chang.ang_morning_server.common.oauth

data class OAuthTokenInfo(
    val accessToken: String
)

data class OAuthUserInfo(
    val email: String,
    val nickname: String
)

interface OAuthClient {
    fun getToken(code: String): OAuthTokenInfo
    fun getUserInfo(accessToken: String): OAuthUserInfo
}