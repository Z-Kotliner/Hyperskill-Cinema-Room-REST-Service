package cinema.datalayer

/**
 *  A file which takes the place of data source holding and providing the cinema Hall related data
 */

const val ROWS = 9
const val COLS = 9


// Cinema seat holder - initialize each seat with A - Available , X - booked
private val seatMatrix = Array(ROWS) { row -> Array(COLS) { if (row < 4) Pair("A", 10) else Pair("A", 8) } }

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

fun isSeatAvailable(row: Int, col: Int): Boolean = seatMatrix[row][col].first == "A"

fun purchaseSeatTicket(row: Int, col: Int): SeatTicket? {
    if (isSeatAvailable(row, col)) {
        seatMatrix[row][col] = seatMatrix[row][col].copy(first = "X")
        return SeatTicket(row, col, seatMatrix[row][col].second)
    } else {
        return null
    }
}