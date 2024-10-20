package chang.ang_morning_server.services.maps.domain

class Place(
    val city: String,
    val formattedAddress: String,
    val zipCode: String,
    val latitude: Double,
    val longitude: Double
) {
}