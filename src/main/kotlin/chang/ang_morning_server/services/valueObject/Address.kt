package chang.ang_morning_server.services.valueObject

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val mainAddress: String,
    val subAddress: String,
    val zipCode: String,
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        fun of(mainAddress: String, subAddress: String, zipCode: String, latitude: Double, longitude: Double): Address {
            return Address(mainAddress, subAddress, zipCode, latitude, longitude)
        }
    }

    fun with(
        mainAddress: String? = null,
        subAddress: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        zipCode: String? = null
    ): Address {
        return this.copy(
            mainAddress = mainAddress ?: this.mainAddress,
            subAddress = subAddress ?: this.subAddress,
            latitude = latitude ?: this.latitude,
            longitude = longitude ?: this.longitude,
            zipCode = zipCode ?: this.zipCode
        )
    }
}