package uz.isystem.tmdbapp.core.models.response.login.acountDetail


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Avatar(
    @SerializedName("gravatar")
    val gravatar: Gravatar,
    @SerializedName("tmdb")
    val tmdb: Tmdb
)