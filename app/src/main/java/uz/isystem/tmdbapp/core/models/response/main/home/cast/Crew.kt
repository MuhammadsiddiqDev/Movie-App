package uz.isystem.tmdbapp.core.models.response.main.home.cast


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Crew(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("credit_id")
    val creditId: String, // 55731b8192514111610027d7
    @SerializedName("department")
    val department: String, // Production
    @SerializedName("gender")
    val gender: Int, // 2
    @SerializedName("id")
    val id: Int, // 376
    @SerializedName("job")
    val job: String, // Executive Producer
    @SerializedName("known_for_department")
    val knownForDepartment: String, // Production
    @SerializedName("name")
    val name: String, // Arnon Milchan
    @SerializedName("original_name")
    val originalName: String, // Arnon Milchan
    @SerializedName("popularity")
    val popularity: Double, // 1.702
    @SerializedName("profile_path")
    val profilePath: String // /b2hBExX4NnczNAnLuTBF4kmNhZm.jpg
)