package chang.ang_morning_server.common.exception

import org.springframework.http.HttpStatus


/**
 * 서버와 클라이언트 메시지를 포함하는 인터페이스
 * @property status HTTP 상태 코드
 * @property message 서버 에러 메시지
 * @property errorMessage 클라이언트 에러 메시지
 */
abstract class HttpException(
    val status: HttpStatus,
    override val message: String,
    val errorMessage: String? = "알수없는 오류가 발생했습니다. 잠시 후 다시 시도해주세요.",
) : RuntimeException(message)