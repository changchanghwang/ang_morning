package chang.ang_morning_server.services.members.presentation

import chang.ang_morning_server.services.members.application.MemberService
import chang.ang_morning_server.services.members.command.SignUpCommand
import chang.ang_morning_server.services.members.command.SignUpResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(private val memberService: MemberService) {
    @PostMapping
    fun signUp(@RequestBody command: SignUpCommand): SignUpResponse {
        return this.memberService.signUp(command)
    }
}