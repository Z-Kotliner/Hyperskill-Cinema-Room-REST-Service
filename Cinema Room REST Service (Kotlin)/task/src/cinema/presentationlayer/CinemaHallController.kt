package cinema.presentationlayer

import cinema.businesslayer.CinemaHallService
import cinema.businesslayer.PurchaseResult
import cinema.datalayer.CinemaHall
import cinema.datalayer.Seat
import cinema.datalayer.SeatTicket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * This is the main Controller class for the Cinema Hall that intercepts incoming requests.
 *
 *  @property cinemaHallService the service class injected utilized by this controller
 */
@RestController
class CinemaHallController {
    @Autowired
    private lateinit var cinemaHallService: CinemaHallService

    @GetMapping("/seats")
    fun getMovieTheatreInfo(): CinemaHall {
        return cinemaHallService.getCinemaHallInfo()
    }

    @PostMapping("/purchase")
    fun purchaseSeatTicket(@RequestBody seat: Seat): SeatTicket {
        try {
            require(seat.row in 1..9 && seat.column in 1..9)

            when (val purchaseTicketForSeat = cinemaHallService.purchaseTicketForASeat(seat.row, seat.column)) {
                is PurchaseResult.Success -> return purchaseTicketForSeat.ticket
                is PurchaseResult.Error -> throw SeatReservationException(
                    purchaseTicketForSeat.error
                )
            }
        } catch (ex: IllegalArgumentException) {
            throw SeatReservationException(
                "The number of a row or a column is out of bounds!"
            )
        }
    }

    class SeatReservationException(error: String) : RuntimeException(error)
}