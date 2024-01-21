package uz.isystem.tmdbapp.core.models.request.watch


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AddWatchRequest(
    @SerializedName("media_id")
    val mediaId: Int, // 11
    @SerializedName("media_type")
    val mediaType: String, // movie
    @SerializedName("watchlist")
    val watchlist: Boolean // true
)