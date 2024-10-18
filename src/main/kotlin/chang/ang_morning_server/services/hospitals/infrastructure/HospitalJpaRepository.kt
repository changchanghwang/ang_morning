package chang.ang_morning_server.services.hospitals.infrastructure

import chang.ang_morning_server.services.hospitals.domain.Hospital
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface HospitalJpaRepository : JpaRepository<Hospital, UUID> {
}