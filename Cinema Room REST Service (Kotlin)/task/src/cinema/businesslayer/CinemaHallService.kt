package cinema.businesslayer

import cinema.datalayer.CinemaHall
import cinema.datalayer.SeatTicket
import cinema.persistencelayer.CinemaHallRepository
import org.springframework.stereotype.Service

/**
 *  A class representing Service which is an intermediate between the presentation and repository layers.
 *  Apply business rules if any.
 *
 *  @param cinemaHallRepository repository from persistence layer
 */
@Service
class CinemaHallService(private val cinemaHallRepository: CinemaHallRepository) {

    fun getCinemaHallInfo(): CinemaHall {
        return cinemaHallRepository.getCinemaInfo()
    }

    fun purchaseTicketForASeat(row: Int, col: Int): PurchaseResult {
        val seatTicket = cinemaHallRepository.purchaseTicketForSeat(row, col)

        return if (seatTicket != null) {
            PurchaseResult.Success(seatTicket)
        } else {
            PurchaseResult.Error("The ticket has been already purchased!")
        }
    }
}

sealed class PurchaseResult {
    data class Success(val ticket: SeatTicket) : PurchaseResult()
    data class Error(val error: String) : PurchaseResult()
}