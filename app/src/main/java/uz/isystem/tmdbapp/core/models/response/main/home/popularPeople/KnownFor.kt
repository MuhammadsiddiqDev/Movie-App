package uz.isystem.tmdbapp.core.models.response.main.home.popularPeople


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class KnownFor(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /wVTYlkKPKrljJfugXN7UlLNjtuJ.jpg
    @SerializedName("first_air_date")
    val firstAirDate: String, // 2011-04-17
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 206647
    @SerializedName("media_type")
    val mediaType: String, // movie
    @SerializedName("name")
    val name: String, // Game of Thrones
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_name")
    val originalName: String, // Game of Thrones
    @SerializedName("original_title")
    val originalTitle: String, // Spectre
    @SerializedName("overview")
    val overview: String, // A cryptic message from Bond’s past sends him on a trail to uncover a sinister organization. While M battles political forces to keep the secret service alive, Bond peels back the layers of deceit to reveal the terrible truth behind SPECTRE.
    @SerializedName("popularity")
    val popularity: Double, // 7.090211
    @SerializedName("poster_path")
    val posterPath: String, // /hE24GYddaxB9MVZl1CaiI86M3kp.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2015-10-26
    @SerializedName("title")
    val title: String, // Spectre
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 6.2
    @SerializedName("vote_count")
    val voteCount: Int // 2956
)