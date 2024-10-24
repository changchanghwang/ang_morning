package chang.ang_morning_server.services.members.infrastructure

import chang.ang_morning_server.services.members.domain.Member
import chang.ang_morning_server.services.members.domain.MemberRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MemberRepositoryImpl(private val jpaRepository: MemberJpaRepository) : MemberRepository {
    override fun save(member: Member): Member {
        return this.jpaRepository.save(member)
    }

    override fun findByEmail(email: String): Member? {
        return this.jpaRepository.findByEmail(email).orElse(null)
    }

    override fun findById(id: UUID): Member? {
        return this.jpaRepository.findById(id).orElse(null)
    }
}