package chang.ang_morning_server.services.maps.application

import chang.ang_morning_server.services.maps.domain.PlaceRepository
import chang.ang_morning_server.services.valueObject.Address
import org.springframework.stereotype.Service

@Service
class MapService(private val placeRepository: PlaceRepository) {
    fun searchAddress(address: String): Address? {
        val place = this.placeRepository.findByAddress(address)
        if (place != null) {
            return Address(place.formattedAddress, "", place.zipCode, place.latitude, place.longitude)
        }
        return null
    }
}