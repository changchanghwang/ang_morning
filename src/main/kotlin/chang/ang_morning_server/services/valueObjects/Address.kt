package chang.ang_morning_server.services.valueObjects

import jakarta.persistence.Embeddable

@Embeddable
class Address(val mainAddress: String, val subAddress: String, val latitude: Double, val longitude: Double) {
    companion object {
        fun of(mainAddress: String, subAddress: String, latitude: Double, longitude: Double): Address {
            return Address(mainAddress, subAddress, latitude, longitude)
        }
    }
}