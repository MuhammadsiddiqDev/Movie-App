package uz.isystem.tmdbapp.core.models.request.createSessionWithLogin


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RequestCreateSessionWIthLogin(
    @SerializedName("username")
    val username: String, // johnny_appleseed
    @SerializedName("password")
    val password: String, // test123
    @SerializedName("request_token")
    val requestToken: String
)