package cinema.businesslayer.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

/**
 *  This class is auto-detected and acts as global exception handler.
 */
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(SeatTicketingException::class)
    fun handleSeatReservationArgumentNotValid(
        ex: SeatTicketingException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["error"] = ex.message.toString()

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AuthorizationException::class)
    fun handleAuthorizationException(
        ex: AuthorizationException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["error"] = ex.message.toString()

        return ResponseEntity(body, HttpStatus.UNAUTHORIZED)
    }
}