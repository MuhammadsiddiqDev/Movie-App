package uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Genre(
    @SerializedName("id")
    val id: Int, // 18
    @SerializedName("name")
    val name: String // Drama
)