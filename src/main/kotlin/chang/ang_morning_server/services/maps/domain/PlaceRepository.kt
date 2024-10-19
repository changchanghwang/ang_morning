package chang.ang_morning_server.services.maps.domain

interface PlaceRepository {
    fun findByAddress(address: String): Place
}