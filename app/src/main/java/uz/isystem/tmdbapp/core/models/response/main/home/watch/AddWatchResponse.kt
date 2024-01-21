package uz.isystem.tmdbapp.core.models.response.main.home.watch


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AddWatchResponse(
    @SerializedName("status_code")
    val statusCode: Int, // 5
    @SerializedName("status_message")
    val statusMessage: String, // Invalid parameters: Your request parameters are incorrect.
    @SerializedName("success")
    val success: Boolean // false
)