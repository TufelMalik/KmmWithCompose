package api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import model.MovieRemote

class MovieService : KtorApi() {

    suspend fun getMovies(page: Int = 1): MovieResponse = client.get {
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()

    suspend fun getMovie(movieId: Int): MovieRemote = client.get {
        pathUrl("movie/$movieId")
    }.body()


}