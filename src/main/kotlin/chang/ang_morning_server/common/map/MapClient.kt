package chang.ang_morning_server.common.map

import reactor.core.publisher.Mono

interface MapClient {
    fun geocode(address: String): Mono<MapResponse>
}