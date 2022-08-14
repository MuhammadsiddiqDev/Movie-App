package uz.isystem.tmdbapp.core.models.response.main.home.castSimilar


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData

@Keep
data class CastSimilarResponse(
    @SerializedName("cast")
    val cast: List<MovieData>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int // 287
)