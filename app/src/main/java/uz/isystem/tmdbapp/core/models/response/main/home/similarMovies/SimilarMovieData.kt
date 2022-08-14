package uz.isystem.tmdbapp.core.models.response.main.home.similarMovies


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SimilarMovieData(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: Any, // null
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 106912
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Darna! Ang Pagbabalik
    @SerializedName("overview")
    val overview: String, // Valentina, Darna's snake-haired arch enemy, is trying to take over the Phillipines through subliminal messages on religious TV shows. Darna has her own problems, however, as she has lost her magic pearl and with it the ability to transform into her scantily clad super self. Trapped as her alter-ego, the plucky reporter Narda, she must try to regain the pearl and foil Valentina's plans.
    @SerializedName("popularity")
    val popularity: Double, // 1.012564
    @SerializedName("poster_path")
    val posterPath: Any, // null
    @SerializedName("release_date")
    val releaseDate: String, // 1994-05-09
    @SerializedName("title")
    val title: String, // Darna: The Return
    @SerializedName("video")
    val video: Boolean, // false
//    @SerializedName("vote_average")
//    val voteAverage: Int, // 0
    @SerializedName("vote_count")
    val voteCount: Int // 0
)