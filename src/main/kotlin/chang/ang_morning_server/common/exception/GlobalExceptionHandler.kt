package chang.ang_morning_server.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = exception.bindingResult.fieldErrors.map { "${it.field}: ${it.defaultMessage}" }
        val errorMap = mutableMapOf("error" to errors[0])
        return ResponseEntity(errorMap, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpException::class)
    fun badRequestExceptions(exception: HttpException): ResponseEntity<Map<String, String?>> {
        // TODO: logger
        println(exception.message)
        val errorMap = mutableMapOf("error" to exception.errorMessage)
        return ResponseEntity(errorMap, exception.status)
    }
}