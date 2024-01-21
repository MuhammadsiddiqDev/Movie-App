package uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.isystem.tmdbapp.core.models.response.main.home.BaseData

@Keep
data class NowPlayingResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<MovieData>,
    @SerializedName("total_pages")
    val totalPages: Int, // 33
    @SerializedName("total_results")
    val totalResults: Int // 649
) : BaseData() {
    override fun getType(): Int {
        return TYPE_NOW_PLAYING
    }
}