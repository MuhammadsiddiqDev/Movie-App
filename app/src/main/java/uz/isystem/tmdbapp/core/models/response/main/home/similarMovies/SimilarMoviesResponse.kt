package uz.isystem.tmdbapp.core.models.response.main.home.similarMovies


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData

@Keep
data class SimilarMoviesResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<MovieData>,
    @SerializedName("total_pages")
    val totalPages: Int, // 9
    @SerializedName("total_results")
    val totalResults: Int // 168
)