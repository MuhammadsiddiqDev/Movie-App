package uz.isystem.tmdbapp.core.models.response.main.home.topRatedMovie


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData
import uz.isystem.tmdbapp.ui.main.home.BaseData

@Keep
data class TopRatedResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<MovieData>,
    @SerializedName("total_pages")
    val totalPages: Int, // 261
    @SerializedName("total_results")
    val totalResults: Int // 5206
) : BaseData() {
    override fun getType(): Int {
        return TYPE_TOP_RATED
    }
}