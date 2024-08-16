package cinema.datalayer

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *  A model class representing a Cinema Theatre Hall Statistics data.
 *
 *  @param currentIncome  the total income of sold tickets.
 *  @param numOfAvailableSeats the number of seats that are available.
 *  @param numOfPurchasedTickets the number of purchased tickets.
 */
data class CinemaHallStat(
    @JsonProperty("current_income") val currentIncome: Int,
    @JsonProperty("number_of_available_seats") val numOfAvailableSeats: Int,
    @JsonProperty("number_of_purchased_tickets") val numOfPurchasedTickets: Int
)
