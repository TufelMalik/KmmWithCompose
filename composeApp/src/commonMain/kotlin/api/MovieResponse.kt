package api

import kotlinx.serialization.Serializable
import model.MovieRemote

@Serializable
data class MovieResponse(
    val results: List<MovieRemote>
)