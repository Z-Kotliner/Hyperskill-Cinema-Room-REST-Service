package cinema.datalayer

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *  A model class representing a Cinema Theatre Hall with seats arranged in rows and columns
 *  @param totalRows - total number of rows in the Hall
 *  @param totalCols - total number of columns in the Hall
 */
data class CinemaHall(
    @JsonProperty("total_rows") val totalRows: Int,
    @JsonProperty("total_columns") val totalCols: Int,
    @JsonProperty("available_seats") val availableSeats: List<Seat>
)