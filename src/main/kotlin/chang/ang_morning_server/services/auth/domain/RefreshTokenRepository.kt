package chang.ang_morning_server.services.auth.domain

import java.util.*

interface RefreshTokenRepository {
    fun save(refreshToken: RefreshToken): RefreshToken
    fun findByToken(token: String): RefreshToken?
    fun deleteByToken(token: String)
    fun existsByTokenAndMemberId(token: String, memberId: UUID): Boolean
    fun deleteByMemberIdAndClientInfo(memberId: UUID, clientInfo: String)
}