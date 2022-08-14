package uz.isystem.tmdbapp.core.models.response.main.home.genres


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)