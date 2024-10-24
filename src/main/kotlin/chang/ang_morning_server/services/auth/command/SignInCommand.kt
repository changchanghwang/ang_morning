package chang.ang_morning_server.services.auth.command

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignInCommand(
    @field:Email(message = "이메일 형식이 올바르지 않습니다.")
    val email: String,
    @field:NotBlank(message = "비밀번호를 입력해주세요.")
    val password: String,
)
