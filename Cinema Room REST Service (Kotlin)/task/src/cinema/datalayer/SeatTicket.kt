package cinema.datalayer

/**
 *  A model class that represents a specific Ticket for a seat identified by row and column
 *
 *  @param row  row id of a seat
 *  @param column column id of a seat
 *  @param price the price of a ticker for a specific seat
 */
data class SeatTicket(val row: Int, val column: Int, val price: Int)
