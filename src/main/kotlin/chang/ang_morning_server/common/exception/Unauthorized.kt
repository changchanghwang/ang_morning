package chang.ang_morning_server.common.exception

import org.springframework.http.HttpStatus

class Unauthorized(
    message: String,
    errorMessage: String?
) : HttpException(HttpStatus.UNAUTHORIZED, message, errorMessage) {
}