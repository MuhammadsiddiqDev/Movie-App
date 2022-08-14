package uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieDetailsResponse(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /fCayJrkfRaCRCTh8GqN30f8oyQF.jpg
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any, // null
    @SerializedName("budget")
    val budget: Int, // 63000000
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int, // 550
    @SerializedName("imdb_id")
    val imdbId: String, // tt0137523
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Fight Club
    @SerializedName("overview")
    val overview: String, // A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground "fight clubs" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.
    @SerializedName("popularity")
    val popularity: Double, // 0.5
    @SerializedName("poster_path")
    val posterPath: Any, // null
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate: String, // 1999-10-12
    @SerializedName("revenue")
    val revenue: Int, // 100853753
    @SerializedName("runtime")
    val runtime: Int, // 139
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String, // Released
    @SerializedName("tagline")
    val tagline: String, // How much can you know about yourself if you've never been in a fight?
    @SerializedName("title")
    val title: String, // Fight Club
    @SerializedName("video")
    val video: Boolean, // false
//    @SerializedName("vote_average")
//    val voteAverage: Double, // 7.8
    @SerializedName("vote_count")
    val voteCount: Int // 3439
)