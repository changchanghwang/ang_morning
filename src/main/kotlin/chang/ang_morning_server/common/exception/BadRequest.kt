package chang.ang_morning_server.common.exception

import org.springframework.http.HttpStatus

class BadRequest(
    message: String,
    errorMessage: String?
) : HttpException(HttpStatus.BAD_REQUEST, message, errorMessage) {
}