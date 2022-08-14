package uz.isystem.tmdbapp.core.models.response.login.acountDetail


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Gravatar(
    @SerializedName("hash")
    val hash: String
)