package cinema.businesslayer

import cinema.datalayer.CinemaHall
import cinema.persistencelayer.CinemaHallRepository
import org.springframework.stereotype.Service

/**
 *  A class representing Service which is an intermediate between the presentation and repository layers.
 *  Apply business rules if any.
 *  @param cinemaHallRepository - repository from persistence layer
 */
@Service
class CinemaHallService(private val cinemaHallRepository: CinemaHallRepository) {
    fun getCinemaHallInfo(): CinemaHall {
        return cinemaHallRepository.getCinemaInfo()
    }
}