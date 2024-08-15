package cinema.datalayer

/**
 *  A model class that represents a purchased Ticket for a seat identified by Token
 *
 *  @param unique token for a purchased ticket
 *  @param ticket identified by row, col, price
 */
data class PurchasedTicket(val token: String, val ticket: SeatTicket)