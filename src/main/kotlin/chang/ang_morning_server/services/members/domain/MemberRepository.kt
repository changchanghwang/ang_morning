package chang.ang_morning_server.services.members.domain

import java.util.*

interface MemberRepository {
    fun save(member: Member): Member
    fun findByEmail(email: String): Member?
    fun findById(id: UUID): Member?
}