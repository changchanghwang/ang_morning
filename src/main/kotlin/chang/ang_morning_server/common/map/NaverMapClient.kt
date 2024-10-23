package chang.ang_morning_server.common.map

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class NaverMapClient(
    @Value("\${naver.api.base-url}") private val baseUrl: String,
    @Value("\${naver.api.api-key-id}") private val apiKeyId: String,
    @Value("\${naver.api.api-key}") private val apiKey: String
) : MapClient {
    private val httpClient = WebClient.builder()
        .codecs { configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024) }
        .baseUrl(baseUrl)
        .defaultHeader("x-ncp-apigw-api-key-id", apiKeyId)
        .defaultHeader("x-ncp-apigw-api-key", apiKey)
        .defaultHeader("Accept", "application/json")
        .build()

    override fun geocode(address: String): Mono<MapResponse> {
        return this.httpClient.get().uri { uriBuilder ->
            uriBuilder
                .path("/map-geocode/v2/geocode")
                .queryParam("query", address)
                .build()
        }.retrieve().bodyToMono(NaverGeocodeResponse::class.java)
            .flatMap { response ->
                if (response.status == "OK" && response.addresses.isNotEmpty()) {
                    Mono.just(MapResponse(response.meta.totalCount, response.addresses.map { res ->
                        FormattedAddress(
                            res.addressElements.find { it.types.find { type -> type == AddressElementType.SIDO } != null }?.longName
                                ?: "",
                            res.roadAddress,
                            res.addressElements.find { it.types.find { type -> type == AddressElementType.POSTAL_CODE } != null }?.longName
                                ?: "",
                            Location(res.y.toDouble(), res.x.toDouble())
                        )
                    }))
                } else {
                    Mono.error(Exception("Can not find address($address)."))
                }
            }
    }
}