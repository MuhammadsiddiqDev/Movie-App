package uz.isystem.tmdbapp.core.models.response.main.home.genres


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Genre(
    @SerializedName("id")
    val id: Int, // 28
    @SerializedName("name")
    val name: String // Action
)