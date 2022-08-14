package uz.isystem.tmdbapp.core.models.response.main.home.cast


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CastResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int // 550
)