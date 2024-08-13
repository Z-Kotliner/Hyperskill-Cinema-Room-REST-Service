package cinema.datalayer

/**
 *  A file which takes the place of data source holding and providing the cinema Hall related data
 */

const val ROWS = 9
const val COLS = 9


// Cinema seat holder - initialize each seat with A - Available
private val seatMatrix = Array(ROWS) { Array(COLS) { "A" } }

fun getCinemaHallInfo(): CinemaHall {
    val seatInfo = getAvailableSeats()
    return CinemaHall(ROWS, COLS, seatInfo)
}

private fun getAvailableSeats(): List<Seat> {
    val seatInfo = mutableListOf<Seat>()
    seatMatrix.forEachIndexed { i, row ->
        row.forEachIndexed { j, col ->
            if (col == "A") seatInfo.add(Seat(i + 1, j + 1))
        }
    }
    return seatInfo
}