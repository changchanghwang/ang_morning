package chang.ang_morning_server.services.auth.infrastructure

import chang.ang_morning_server.services.auth.domain.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RefreshTokenJpaRepository : JpaRepository<RefreshToken, Long> {
    fun findByToken(token: String): RefreshToken?
    fun deleteByToken(token: String)

    @Query("SELECT COUNT(r) > 0 FROM refreshToken r WHERE r.token = :token AND r.member.id = :memberId")
    fun existsByTokenAndMemberId(token: String, memberId: UUID): Boolean

    fun deleteByMemberIdAndClientInfo(memberId: UUID, clientInfo: String)
}