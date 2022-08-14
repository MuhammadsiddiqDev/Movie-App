package uz.isystem.tmdbapp.core.models.response.login.createRequestToken


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CreateRequestTokenResponse(
    @SerializedName("success")
    val success: Boolean, // true
    @SerializedName("expires_at")
    val expiresAt: String, // 2022-04-10 06:24:46 UTC
    @SerializedName("request_token")
    val requestToken: String // c9e19e0f01be88a29bb0d826848653aa1c5b556d
)