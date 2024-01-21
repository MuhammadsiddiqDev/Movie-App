package uz.isystem.tmdbapp.core.models.response.main.home.sliderMovie


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.isystem.tmdbapp.core.models.response.main.home.BaseData
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.MovieData

@Keep
data class SliderResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<MovieData>,
    @SerializedName("total_pages")
    val totalPages: Int, // 792
    @SerializedName("total_results")
    val totalResults: Int // 15831
) : BaseData() {
    override fun getType(): Int {
        return TYPE_SLIDER
    }
}