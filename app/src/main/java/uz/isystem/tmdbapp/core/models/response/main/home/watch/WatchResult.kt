package uz.isystem.tmdbapp.core.models.response.main.home.watch


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WatchResult(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /jXJxMcVoEuXzym3vFnjqDW4ifo6.jpg
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 572802
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Aquaman and the Lost Kingdom
    @SerializedName("overview")
    val overview: String, // Black Manta, still driven by the need to avenge his father's death and wielding the power of the mythic Black Trident, will stop at nothing to take Aquaman down once and for all. To defeat him, Aquaman must turn to his imprisoned brother Orm, the former King of Atlantis, to forge an unlikely alliance in order to save the world from irreversible destruction.
    @SerializedName("popularity")
    val popularity: Double, // 1482.782
    @SerializedName("poster_path")
    val posterPath: String, // /qJiWKzdRScI5OcRQqOu3qdMZKXY.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2023-12-20
    @SerializedName("title")
    val title: String, // Aquaman and the Lost Kingdom
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 6.5
    @SerializedName("vote_count")
    val voteCount: Int // 320
)