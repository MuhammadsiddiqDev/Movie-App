package uz.isystem.tmdbapp.core.models.response.login.createSession


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CreateSessionResponse(
    @SerializedName("success")
    val success: Boolean, // true
    @SerializedName("session_id")
    val sessionId: String
)