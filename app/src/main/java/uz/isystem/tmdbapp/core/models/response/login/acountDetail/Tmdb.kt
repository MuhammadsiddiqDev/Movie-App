package uz.isystem.tmdbapp.core.models.response.login.acountDetail


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Tmdb(
    @SerializedName("avatar_path")
    val avatarPath: String // /AcdbqK5PPIJGwEM1yLlKaNXSuhm.jpg
)