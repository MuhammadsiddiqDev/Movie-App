package uz.isystem.tmdbapp.core.models.response.main.home.search.actior


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class KnownFor(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /6BDBY1KhGHznQfCjHdGyIwNp30x.jpg
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 132316
    @SerializedName("media_type")
    val mediaType: String, // movie
    @SerializedName("original_language")
    val originalLanguage: String, // hi
    @SerializedName("original_title")
    val originalTitle: String, // जब तक है जान
    @SerializedName("overview")
    val overview: String, // A bomb disposal expert becomes bitter and lonely and is unable to fall in love until he is forced to deal with his past.
    @SerializedName("popularity")
    val popularity: Double, // 17.272
    @SerializedName("poster_path")
    val posterPath: String, // /1TlzJZrzUlaC95Noqt3oMoxQxCu.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2012-11-13
    @SerializedName("title")
    val title: String, // Jab Tak Hai Jaan
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 7.112
    @SerializedName("vote_count")
    val voteCount: Int // 236
)