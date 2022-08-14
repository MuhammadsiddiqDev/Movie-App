package uz.isystem.tmdbapp.core.models.request.createSession


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RequestCreateSession(
    @SerializedName("request_token")
    val requestToken: String
)