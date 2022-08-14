package uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieImage


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieImagesResponse(
    @SerializedName("backdrops")
    val backdrops: List<Backdrop>,
    @SerializedName("id")
    val id: Int, // 550
    @SerializedName("posters")
    val posters: List<Poster>
)