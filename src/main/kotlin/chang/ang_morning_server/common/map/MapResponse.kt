package chang.ang_morning_server.common.map

data class MapResponse(val count: Int, val formattedAddresses: List<FormattedAddress>)

data class FormattedAddress(val address: String, val zipCode: String, val location: Location)

data class Location(val latitude: Double, val longitude: Double)
