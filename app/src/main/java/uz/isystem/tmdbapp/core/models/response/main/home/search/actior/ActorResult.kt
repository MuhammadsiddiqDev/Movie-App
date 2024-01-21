package uz.isystem.tmdbapp.core.models.response.main.home.search.actior


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ActorResult(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("gender")
    val gender: Int, // 1
    @SerializedName("id")
    val id: Int, // 81869
    @SerializedName("known_for")
    val knownFor: List<KnownFor>,
    @SerializedName("known_for_department")
    val knownForDepartment: String, // Acting
    @SerializedName("name")
    val name: String, // Katrina Kaif
    @SerializedName("original_name")
    val originalName: String, // Katrina Kaif
    @SerializedName("popularity")
    val popularity: Double, // 19.589
    @SerializedName("profile_path")
    val profilePath: String // /sGxjQQ2ymrrplbRqFjwiJiUdc5w.jpg
)