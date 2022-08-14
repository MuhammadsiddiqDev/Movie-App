package uz.isystem.tmdbapp.core.models.response.main.home.sliderMovie


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Results(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /bOGkgRGdhrBYJSLpXaxhXVstddV.jpg
    @SerializedName("first_air_date")
    val firstAirDate: String, // 2018-08-17
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 299536
    @SerializedName("name")
    val name: String, // Disenchantment
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_name")
    val originalName: String, // Disenchantment
    @SerializedName("original_title")
    val originalTitle: String, // Avengers: Infinity War
    @SerializedName("overview")
    val overview: String, // As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.
    @SerializedName("popularity")
    val popularity: Double, // 358.799
    @SerializedName("poster_path")
    val posterPath: String, // /7WsyChQLEftFiDOVTGkv3hFpyyt.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2018-04-25
    @SerializedName("title")
    val title: String, // Avengers: Infinity War
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 8.3
    @SerializedName("vote_count")
    val voteCount: Int // 6937
)