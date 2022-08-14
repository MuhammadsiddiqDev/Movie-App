package uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieTrailer


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieTrailerResponse(
    @SerializedName("id")
    val id: Int, // 550
    @SerializedName("results")
    val results: List<VideoData>
)