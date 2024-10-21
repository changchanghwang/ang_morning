package chang.ang_morning_server.services.hospitals.queries

import chang.ang_morning_server.services.valueObject.Address
import java.util.*

data class HospitalQueryResponse(
    val items: List<HospitalOutput>,
    val count: Long,
)

data class HospitalOutput(
    val id: UUID,
    val name: String,
    val address: Address
)
