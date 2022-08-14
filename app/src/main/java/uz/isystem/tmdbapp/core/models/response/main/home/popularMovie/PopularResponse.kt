package uz.isystem.tmdbapp.core.models.response.main.home.popularMovie


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData
import uz.isystem.tmdbapp.ui.main.home.BaseData

@Keep
data class PopularResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<MovieData>,
    @SerializedName("total_pages")
    val totalPages: Int, // 982
    @SerializedName("total_results")
    val totalResults: Int // 19629
) : BaseData() {
    override fun getType(): Int {
        return TYPE_POPULAR
    }
}