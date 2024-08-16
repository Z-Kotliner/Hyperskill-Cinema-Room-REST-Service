package cinema.presentationlayer

import cinema.businesslayer.CinemaHallService
import cinema.datalayer.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * This is the main Controller class for the Cinema Hall that intercepts incoming requests.
 *
 * @property cinemaHallService the service class injected & utilized by this controller
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
    fun purchaseSeatTicket(@RequestBody seat: Seat): PurchasedTicket {
        return cinemaHallService.purchaseTicketForASeat(seat.row, seat.column)
    }

    @PostMapping("/return")
    fun returnSeatTicket(@RequestBody token: Map<String, String>): Map<String, SeatTicket> {
        return cinemaHallService.returnSeatTicket(token)
    }

    @GetMapping("/stats")
    fun getStatistics(@RequestParam(value = "password", required = false, defaultValue = "") password: String): CinemaHallStat {
        return cinemaHallService.getCinemaHallStat(password)
    }
}