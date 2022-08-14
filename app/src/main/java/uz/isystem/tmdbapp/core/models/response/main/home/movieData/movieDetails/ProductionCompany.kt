package uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductionCompany(
    @SerializedName("id")
    val id: Int, // 508
    @SerializedName("logo_path")
    val logoPath: String, // /7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png
    @SerializedName("name")
    val name: String, // Regency Enterprises
    @SerializedName("origin_country")
    val originCountry: String // US
)