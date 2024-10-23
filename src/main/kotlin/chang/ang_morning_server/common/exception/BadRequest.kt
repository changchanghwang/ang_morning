package chang.ang_morning_server.common.exception

import org.springframework.http.HttpStatus

/**
 * @param message 서버 에러 메세지
 * @param errorMessage client 에러 메세지
 */
class BadRequest(
    message: String,
    errorMessage: String?
) : HttpException(HttpStatus.BAD_REQUEST, message, errorMessage) {
}