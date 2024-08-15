package cinema.datalayer

import java.util.UUID

/**
 *  A file which takes the place of data source holding and providing the cinema Hall related data
 */

const val ROWS = 9
const val COLS = 9


/**
 * Cinema seat holder is a 2DMatrix Array that represents cinema hall rows and columns
 * Each array cell represents a seat and holds a Triple of information about a seat
 *
 *  1. Availability - initialize each seat with A - Available , X - booked
 *  2. Price - 10 for seats in row 1..4 and 8 for others
 *  3. token - unique identifier for a booked seat, ""(Default) for non-booked seats
 */

private val seatMatrix = Array(ROWS) { row -> Array(COLS) { if (row < 4) Triple("A", 10, "") else Triple("A", 8, "") } }

fun getCinemaHallInfo(): CinemaHall {
    val seatInfo = getAvailableSeats()
    return CinemaHall(ROWS, COLS, seatInfo)
}

private fun getAvailableSeats(): List<SeatTicket> {
    val seatInfo = mutableListOf<SeatTicket>()
    seatMatrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, col ->
            if (col.first == "A") seatInfo.add(SeatTicket(i + 1, j + 1, col.second))
        }
    }
    return seatInfo
}

fun isSeatAvailable(row: Int, col: Int): Boolean = seatMatrix[row - 1][col - 1].first == "A"

fun purchaseSeatTicket(row: Int, col: Int): PurchasedTicket? {
    if (isSeatAvailable(row, col)) {
        val token = UUID.randomUUID().toString()
        seatMatrix[row - 1][col - 1] = seatMatrix[row - 1][col - 1].copy(first = "X", third = token)
        val seatTicket = SeatTicket(row, col, seatMatrix[row][col].second)
        return PurchasedTicket(token, seatTicket)
    } else {
        return null
    }
}

fun returnSeatTicket(tokenMap: Map<String, String>): Map<String, SeatTicket>? {
    val token = tokenMap["token"]

    // Check if token is valid and if a seat with the specified token exists
    if (!token.isNullOrEmpty()) {
        val seatTicket = identifySeatWithToken(token)
        if (seatTicket != null) {
            // unbook seat - make it Available & clear the token
            seatMatrix[seatTicket.row - 1][seatTicket.column - 1] =
                seatMatrix[seatTicket.row - 1][seatTicket.column - 1].copy(first = "A", third = "")

            // return unbooked seat ticket
            return mapOf("returned_ticket" to seatTicket)
        }
    }

    return null
}

fun identifySeatWithToken(token: String): SeatTicket? {
    seatMatrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, col ->
            if (col.third == token) {
                val seatTicket = SeatTicket(i + 1, j + 1, col.second)
                return seatTicket
            }
        }
    }
    return null
}

