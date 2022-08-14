package uz.isystem.tmdbapp.core.models.response.main.home.castSimilar


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CastSimilar(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /jet7PQMY8aVzxBvkpG4P0eQI2n6.jpg
    @SerializedName("character")
    val character: String, // Tristan Ludlow
    @SerializedName("credit_id")
    val creditId: String, // 52fe43c4c3a36847f806e20d
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 4476
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Legends of the Fall
    @SerializedName("overview")
    val overview: String, // An epic tale of three brothers and their father living in the remote wilderness of 1900s USA and how their lives are affected by nature, history, war, and love.
    @SerializedName("popularity")
    val popularity: Double, // 2.356929
    @SerializedName("poster_path")
    val posterPath: String, // /uh0sJcx3SLtclJSuKAXl6Tt6AV0.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 1994-12-16
    @SerializedName("title")
    val title: String, // Legends of the Fall
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 7.2
    @SerializedName("vote_count")
    val voteCount: Int // 568
)