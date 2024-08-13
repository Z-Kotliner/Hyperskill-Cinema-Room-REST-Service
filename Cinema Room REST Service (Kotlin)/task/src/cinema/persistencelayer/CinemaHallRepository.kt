package cinema.persistencelayer

import cinema.datalayer.getCinemaHallInfo
import org.springframework.stereotype.Repository

/**
 * A repository class which interacts with our data source - imitates the data access object
 */
@Repository
class CinemaHallRepository {
    fun getCinemaInfo() = getCinemaHallInfo()
}