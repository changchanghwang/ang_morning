package chang.ang_morning_server.services.members.domain.services

import chang.ang_morning_server.services.members.domain.MemberRepository
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Component

@Component
class MemberValidator(private val memberRepository: MemberRepository) {
    fun validateSignUp(email: String) {
        val exist = this.memberRepository.findByEmail(email).isPresent
        if (exist) {
            throw BadRequestException("Duplicated Email($email)")
        }
    }
}