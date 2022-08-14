package uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Dates(
    @SerializedName("maximum")
    val maximum: String, // 2016-09-01
    @SerializedName("minimum")
    val minimum: String // 2016-07-21
)