package uz.isystem.tmdbapp.core.models.response.main.home.popularPeople


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import uz.isystem.tmdbapp.core.models.response.main.home.cast.Cast

@Keep
data class PeoplePopularResponse(
    @SerializedName("page")
    val page: Int, // 1
    @SerializedName("results")
    val results: List<Cast>,
    @SerializedName("total_pages")
    val totalPages: Int, // 984
    @SerializedName("total_results")
    val totalResults: Int // 19671
)