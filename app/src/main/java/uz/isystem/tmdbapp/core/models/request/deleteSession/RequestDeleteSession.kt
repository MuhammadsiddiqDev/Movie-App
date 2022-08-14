package uz.isystem.tmdbapp.core.models.request.deleteSession


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RequestDeleteSession(
    @SerializedName("success")
    val success: Boolean // true
)