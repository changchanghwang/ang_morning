package chang.ang_morning_server.services.maps.infrastructure

import chang.ang_morning_server.common.exception.BadRequest
import chang.ang_morning_server.common.map.MapClient
import chang.ang_morning_server.services.maps.domain.Place
import chang.ang_morning_server.services.maps.domain.PlaceRepository
import org.springframework.stereotype.Repository

@Repository
class PlaceRepositoryImpl(private val mapClient: MapClient) : PlaceRepository {
    override fun findByAddress(address: String): Place {
        val response = this.mapClient.geocode(address).block() ?: throw BadRequest("Invalid address", "주소를 찾을 수 없습니다.")
        val formattedAddress =
            response.formattedAddresses.firstOrNull() ?: throw BadRequest("Invalid address", "주소를 찾을 수 없습니다.")

        return Place(
            formattedAddress.city,
            formattedAddress.address,
            formattedAddress.zipCode,
            formattedAddress.location.latitude,
            formattedAddress.location.longitude
        )
    }
}