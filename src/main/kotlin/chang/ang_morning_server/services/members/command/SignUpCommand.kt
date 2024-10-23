package chang.ang_morning_server.services.members.command

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class SignUpCommand(
    @field:Email(message = "이메일 형식이 올바르지 않습니다.")
    val email: String,

    @field:Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#\$%])[A-Za-z0-9!@#\$%]{8,20}$",
        message = "비밀번호는 영문 대소문자, 숫자, 특수문자(!@#$%)를 포함하여 8자 이상 20자 이하여야 합니다."
    )
    val password: String,

    @field:NotBlank(message = "닉네임을 입력해주세요.")
    val nickname: String
)