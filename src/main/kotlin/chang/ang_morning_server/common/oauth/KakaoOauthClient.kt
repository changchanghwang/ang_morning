package chang.ang_morning_server.common.oauth

import chang.ang_morning_server.common.exception.BadRequest
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.reactive.function.client.WebClient

data class KakaoTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("scope")
    val scope: String,
)

data class KakaoUserResponse(
    val id: Long,
    @JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccount
) {
    data class KakaoAccount(
        val email: String,
        val profile: Profile
    ) {
        data class Profile(
            val nickname: String
        )
    }
}

class KakaoClient(
    private val webClient: WebClient,
    private val clientId: String,
    private val clientSecret: String,
    private val redirectUri: String
) : OAuthClient {

    override fun getToken(code: String): OAuthTokenInfo {
        val response = webClient.post()
            .uri("https://kauth.kakao.com/oauth/token") {
                it.queryParam("grant_type", "authorization_code")
                    .queryParam("client_id", clientId)
                    .queryParam("client_secret", clientSecret)
                    .queryParam("code", code)
                    .queryParam("redirect_uri", redirectUri)
                    .build()
            }
            .retrieve()
            .bodyToMono(KakaoTokenResponse::class.java)
            // FIXME: is it bad request?
            .block() ?: throw BadRequest("Failed to get token from Kakao", null)

        return OAuthTokenInfo(response.accessToken)
    }

    override fun getUserInfo(accessToken: String): OAuthUserInfo {
        val response = webClient.get()
            .uri("https://kapi.kakao.com/v2/user/me")
            .header("Authorization", "Bearer $accessToken")
            .retrieve()
            .bodyToMono(KakaoUserResponse::class.java)
            // FIXME: is it bad request?
            .block() ?: throw BadRequest("Failed to get user info from Kakao", null)

        return OAuthUserInfo(
            email = response.kakaoAccount.email,
            nickname = response.kakaoAccount.profile.nickname
        )
    }
}