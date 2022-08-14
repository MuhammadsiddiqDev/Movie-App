package uz.isystem.tmdbapp.core.models.response.main.home.search


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SearchResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<SearchData>,
    @SerializedName("total_pages")
    val totalPages: Int, // 1
    @SerializedName("total_results")
    val totalResults: Int // 14
)