package uz.isystem.tmdbapp.core.models.response.main.home.search


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SearchData(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 24428
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // The Avengers
    @SerializedName("overview")
    val overview: String, // When an unexpected enemy emerges and threatens global safety and security, Nick Fury, director of the international peacekeeping agency known as S.H.I.E.L.D., finds himself in need of a team to pull the world back from the brink of disaster. Spanning the globe, a daring recruitment effort begins!
    @SerializedName("popularity")
    val popularity: Double, // 7.353212
    @SerializedName("poster_path")
    val posterPath: String, // /cezWGskPY5x7GaglTTRN4Fugfb8.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2012-04-25
    @SerializedName("title")
    val title: String, // The Avengers
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 7.33
    @SerializedName("vote_count")
    val voteCount: Int // 8503
)