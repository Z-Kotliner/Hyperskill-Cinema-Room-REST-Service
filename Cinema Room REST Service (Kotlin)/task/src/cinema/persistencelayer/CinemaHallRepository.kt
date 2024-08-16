package cinema.persistencelayer

import cinema.datalayer.*
import org.springframework.stereotype.Repository

/**
 * A repository class which interacts with our data source - imitates the data access object
 */
@Repository
class CinemaHallRepository {

    fun getCinemaInfo() = getCinemaHallInfo()

    fun purchaseTicketForSeat(row: Int, col: Int) = purchaseSeatTicket(row, col)

    fun returnTicket(token: Map<String, String>) = returnSeatTicket(token)

    fun getStatistics(password: String): CinemaHallStat? = getCinemaStatistics(password)
}