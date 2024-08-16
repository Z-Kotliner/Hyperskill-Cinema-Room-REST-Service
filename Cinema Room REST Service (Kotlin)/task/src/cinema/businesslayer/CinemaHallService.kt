package cinema.businesslayer

import cinema.businesslayer.exception.AuthorizationException
import cinema.businesslayer.exception.SeatTicketingException
import cinema.datalayer.CinemaHall
import cinema.datalayer.CinemaHallStat
import cinema.datalayer.PurchasedTicket
import cinema.datalayer.SeatTicket
import cinema.persistencelayer.CinemaHallRepository
import org.springframework.stereotype.Service

/**
 *  A class representing Service which is an intermediary between the presentation and repository layers.
 *  Apply business rules if any.
 *
 *  @param cinemaHallRepository repository from persistence layer
 */
@Service
class CinemaHallService(private val cinemaHallRepository: CinemaHallRepository) {

    fun getCinemaHallInfo(): CinemaHall {
        return cinemaHallRepository.getCinemaInfo()
    }

    fun purchaseTicketForASeat(row: Int, col: Int): PurchasedTicket {
        try {
            require(row in 1..9 && col in 1..9)

            return cinemaHallRepository.purchaseTicketForSeat(row, col)
                ?: throw SeatTicketingException("The ticket has been already purchased!")

        } catch (ex: IllegalArgumentException) {
            throw SeatTicketingException("The number of a row or a column is out of bounds!")
        }
    }

    fun returnSeatTicket(token: Map<String, String>): Map<String, SeatTicket> {
        return cinemaHallRepository.returnTicket(token) ?: throw SeatTicketingException("Wrong token!")
    }

    fun getCinemaHallStat(password: String): CinemaHallStat {
        return cinemaHallRepository.getStatistics(password) ?: throw AuthorizationException("The password is wrong!")
    }
}