package uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieImage


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Poster(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double, // 0.666666666666667
    @SerializedName("file_path")
    val filePath: String, // /fpemzjF623QVTe98pCVlwwtFC5N.jpg
    @SerializedName("height")
    val height: Int, // 1800
    @SerializedName("iso_639_1")
    val iso6391: String, // en
//    @SerializedName("vote_average")
//    val voteAverage: Int, // 0
    @SerializedName("vote_count")
    val voteCount: Int, // 0
    @SerializedName("width")
    val width: Int // 1200
)