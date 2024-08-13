package cinema.presentationlayer

import cinema.businesslayer.CinemaHallService
import cinema.datalayer.CinemaHall
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CinemaHallController(private val cinemaHallService: CinemaHallService) {
    @GetMapping("/seats")
    fun getMovieTheatreInfo(): CinemaHall {
        return cinemaHallService.getCinemaHallInfo()
    }
}