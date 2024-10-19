package chang.ang_morning_server.common.map

data class NaverGeocodeResponse(
    val status: String,
    val meta: Meta,
    val addresses: List<NaverAddress>,
    val errorMessage: String?
)

data class Meta(
    val totalCount: Int,
    val page: Int,
    val count: Int
)


data class NaverAddress(
    val roadAddress: String,
    val jibunAddress: String,
    val x: String,
    val y: String,
    val addressElements: List<AddressElement>
)

data class AddressElement(
    val types: List<AddressElementType>,
    val longName: String,
    val shortName: String,
    val code: String,
)

enum class AddressElementType {
    SIDO,
    SIGUGUN,
    DONGMYUN,
    RI,
    ROAD_NAME,
    BUILDING_NUMBER,
    BUILDING_NAME,
    LAND_NUMBER,
    POSTAL_CODE
}



