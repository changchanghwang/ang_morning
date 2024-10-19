package chang.ang_morning_server.services.maps.infrastructure

import chang.ang_morning_server.common.map.MapClient
import chang.ang_morning_server.services.maps.domain.Place
import chang.ang_morning_server.services.maps.domain.PlaceRepository
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Repository

@Repository
class PlaceRepositoryImpl(private val mapClient: MapClient) : PlaceRepository {
    override fun findByAddress(address: String): Place {
        return this.mapClient.geocode(address).map { response ->
            val formattedAddress =
                response.formattedAddresses.firstOrNull() ?: throw BadRequestException("Invalid address")

            // Place 객체 생성
            Place(
                formattedAddress.address,
                formattedAddress.zipCode,
                formattedAddress.location.latitude,
                formattedAddress.location.longitude
            )
        }.block() ?: throw BadRequestException("Invalid address")
    }
}