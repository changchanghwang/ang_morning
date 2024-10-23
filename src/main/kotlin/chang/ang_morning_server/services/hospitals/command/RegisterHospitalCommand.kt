package chang.ang_morning_server.services.hospitals.command

import jakarta.validation.constraints.NotBlank

data class RegisterHospitalCommand(
    @field:NotBlank(message = "이름을 입력해주세요.")
    val name: String,
    @field:NotBlank(message = "주소를 입력해주세요")
    val mainAddress: String,
    val subAddress: String? = ""
)
