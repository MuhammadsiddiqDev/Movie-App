package uz.isystem.tmdbapp.core.models.response.main.home.topRatedMovie


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Result(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /xBKGJQsAIeweesB79KC89FpBrVr.jpg
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 278
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // The Shawshank Redemption
    @SerializedName("overview")
    val overview: String, // Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.
    @SerializedName("popularity")
    val popularity: Double, // 6.741296
    @SerializedName("poster_path")
    val posterPath: String, // /9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 1994-09-10
    @SerializedName("title")
    val title: String, // The Shawshank Redemption
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 8.32
    @SerializedName("vote_count")
    val voteCount: Int // 5238
)