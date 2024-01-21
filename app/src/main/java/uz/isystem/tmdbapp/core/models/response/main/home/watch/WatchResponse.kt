package uz.isystem.tmdbapp.core.models.response.main.home.watch


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WatchResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: ArrayList<WatchResult>,
    @SerializedName("total_pages")
    val totalPages: Int, // 1
    @SerializedName("total_results")
    val totalResults: Int // 1
)