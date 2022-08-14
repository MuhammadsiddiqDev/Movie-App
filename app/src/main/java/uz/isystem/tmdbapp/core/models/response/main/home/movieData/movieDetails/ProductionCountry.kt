package uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String, // US
    @SerializedName("name")
    val name: String // United States of America
)