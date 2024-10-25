package chang.ang_morning_server.common.oauth

import chang.ang_morning_server.services.members.domain.ProviderType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class OauthClientFactory(
    @Value("\${oauth2.kakao.client-id}") private val kakaoClientId: String,
    @Value("\${oauth2.kakao.client-secret}") private val kakaoClientSecret: String,
    @Value("\${oauth2.kakao.redirect-uri}") private val kakaoRedirectUri: String
) {
    private val webClient: WebClient = WebClient.builder().build()

    private val clientMap: Map<ProviderType, OAuthClient> = mapOf(
        ProviderType.KAKAO to KakaoClient(
            webClient,
            kakaoClientId,
            kakaoClientSecret,
            kakaoRedirectUri
        )
        // TODO: 다른 provider client들...
    )

    fun getClient(provider: ProviderType): OAuthClient {
        return clientMap[provider] ?: throw IllegalArgumentException("지원하지 않는 소셜 로그인입니다: $provider")
    }
}