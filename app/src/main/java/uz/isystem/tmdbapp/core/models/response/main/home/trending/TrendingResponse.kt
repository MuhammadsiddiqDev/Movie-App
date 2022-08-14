package uz.isystem.tmdbapp.core.models.response.main.home.trending


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.isystem.tmdbapp.core.models.response.main.home.cast.Cast

@Keep
data class TrendingResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<Cast>,
    @SerializedName("total_pages")
    val totalPages: Int, // 792
    @SerializedName("total_results")
    val totalResults: Int // 15831
)