package chang.ang_morning_server.services.members.domain.services

import chang.ang_morning_server.common.exception.BadRequest
import chang.ang_morning_server.services.members.domain.MemberRepository
import org.springframework.stereotype.Component

@Component
class MemberValidator(private val memberRepository: MemberRepository) {
    fun validateSignUp(email: String) {
        val exist = this.memberRepository.findByEmail(email).isPresent
        if (exist) {
            throw BadRequest("Duplicated Email($email)", "중복된 이메일 입니다.")
        }
    }
}