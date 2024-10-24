package chang.ang_morning_server.services.auth.infrastructure

import chang.ang_morning_server.services.auth.domain.RefreshToken
import chang.ang_morning_server.services.auth.domain.RefreshTokenRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class RefreshTokenRepositoryImpl(
    private val jpaRepository: RefreshTokenJpaRepository
) : RefreshTokenRepository {
    override fun save(refreshToken: RefreshToken): RefreshToken {
        return this.jpaRepository.save(refreshToken)
    }

    override fun findByToken(token: String): RefreshToken? {
        return this.jpaRepository.findByToken(token)
    }

    override fun deleteByToken(token: String) {
        return this.jpaRepository.deleteByToken(token)
    }

    override fun existsByTokenAndMemberId(token: String, memberId: UUID): Boolean {
        return this.jpaRepository.existsByTokenAndMemberId(token, memberId)
    }

    override fun deleteByMemberIdAndClientInfo(memberId: UUID, clientInfo: String) {
        return this.jpaRepository.deleteByMemberIdAndClientInfo(memberId, clientInfo)
    }
}