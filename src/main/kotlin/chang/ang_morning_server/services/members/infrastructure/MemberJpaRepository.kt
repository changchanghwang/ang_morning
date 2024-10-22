package chang.ang_morning_server.services.members.infrastructure

import chang.ang_morning_server.services.members.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberJpaRepository : JpaRepository<Member, UUID> {
    fun findByEmail(email: String): Optional<Member>
}