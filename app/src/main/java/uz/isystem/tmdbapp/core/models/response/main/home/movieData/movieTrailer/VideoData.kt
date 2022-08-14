package uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieTrailer


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VideoData(
    @SerializedName("id")
    val id: String, // 5e382d1b4ca676001453826d
    @SerializedName("iso_3166_1")
    val iso31661: String, // US
    @SerializedName("iso_639_1")
    val iso6391: String, // en
    @SerializedName("key")
    val key: String, // 6JnN1DmbqoU
    @SerializedName("name")
    val name: String, // Fight Club - Theatrical Trailer Remastered in HD
    @SerializedName("official")
    val official: Boolean, // false
    @SerializedName("published_at")
    val publishedAt: String, // 2015-02-26T03:19:25.000Z
    @SerializedName("site")
    val site: String, // YouTube
    @SerializedName("size")
    val size: Int, // 1080
    @SerializedName("type")
    val type: String // Trailer
)