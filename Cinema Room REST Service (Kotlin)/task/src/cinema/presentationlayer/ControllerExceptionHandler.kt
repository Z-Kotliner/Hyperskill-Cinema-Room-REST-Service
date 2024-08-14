package cinema.presentationlayer

import cinema.presentationlayer.CinemaHallController.SeatReservationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

/**
 *  This class is auto-detected to act as exception handler for the controller class
 */
@RestControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(SeatReservationException::class)
    fun handleSeatReservationArgumentNotValid(
        ex: SeatReservationException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["exception"] = ex.javaClass
        body["error"] = ex.message.toString()

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }
}