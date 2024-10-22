package chang.ang_morning_server.services.members.application

import chang.ang_morning_server.services.members.command.SignUpCommand
import chang.ang_morning_server.services.members.command.SignUpResponse
import chang.ang_morning_server.services.members.domain.Member
import chang.ang_morning_server.services.members.domain.MemberRepository
import chang.ang_morning_server.services.members.domain.services.MemberValidator
import chang.ang_morning_server.services.members.domain.services.PasswordService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberValidator: MemberValidator,
    private val passwordService: PasswordService
) {
    @Transactional
    fun signUp(command: SignUpCommand): SignUpResponse {
        this.memberValidator.validateSignUp(command.email)

        val member = Member.of(command.email, command.password, command.nickname, this.passwordService)

        this.memberRepository.save(member)

        return SignUpResponse(member.id)
    }
}