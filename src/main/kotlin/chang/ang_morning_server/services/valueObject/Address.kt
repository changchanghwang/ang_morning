package chang.ang_morning_server.services.valueObject

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val city: String,
    val mainAddress: String,
    val subAddress: String,
    val zipCode: String,
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        fun of(
            city: String,
            mainAddress: String,
            subAddress: String,
            zipCode: String,
            latitude: Double,
            longitude: Double
        ): Address {
            return Address(city, mainAddress, subAddress, zipCode, latitude, longitude)
        }
    }

    fun with(
        city: String? = null,
        mainAddress: String? = null,
        subAddress: String? = null,
        latitude: Double? = null,
        longitude: Double? = null,
        zipCode: String? = null
    ): Address {
        return this.copy(
            city = city ?: this.city,
            mainAddress = mainAddress ?: this.mainAddress,
            subAddress = subAddress ?: this.subAddress,
            latitude = latitude ?: this.latitude,
            longitude = longitude ?: this.longitude,
            zipCode = zipCode ?: this.zipCode
        )
    }
}