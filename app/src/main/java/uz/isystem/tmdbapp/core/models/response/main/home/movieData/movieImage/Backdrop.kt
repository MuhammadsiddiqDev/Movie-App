package uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieImage


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Backdrop(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double, // 1.77777777777778
    @SerializedName("file_path")
    val filePath: String, // /fCayJrkfRaCRCTh8GqN30f8oyQF.jpg
    @SerializedName("height")
    val height: Int, // 720
    @SerializedName("iso_639_1")
    val iso6391: Any, // null
//    @SerializedName("vote_average")
//    val voteAverage: Int, // 0
    @SerializedName("vote_count")
    val voteCount: Int, // 0
    @SerializedName("width")
    val width: Int // 1280
)