package uz.isystem.tmdbapp.core.models.response.main.home.UpcomingMovie


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Result(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 297761
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // Suicide Squad
    @SerializedName("overview")
    val overview: String, // From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.
    @SerializedName("popularity")
    val popularity: Double, // 48.261451
    @SerializedName("poster_path")
    val posterPath: String, // /e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2016-08-03
    @SerializedName("title")
    val title: String, // Suicide Squad
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 5.91
    @SerializedName("vote_count")
    val voteCount: Int // 1466
)