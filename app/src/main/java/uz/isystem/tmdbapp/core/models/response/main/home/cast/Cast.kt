package uz.isystem.tmdbapp.core.models.response.main.home.cast


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Cast(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("cast_id")
    val castId: Int, // 4
    @SerializedName("character")
    val character: String, // The Narrator
    @SerializedName("credit_id")
    val creditId: String, // 52fe4250c3a36847f80149f3
    @SerializedName("gender")
    val gender: Int, // 2
    @SerializedName("id")
    val id: Int, // 819
    @SerializedName("known_for_department")
    val knownForDepartment: String, // Acting
    @SerializedName("name")
    val name: String, // Edward Norton
    @SerializedName("order")
    val order: Int, // 0
    @SerializedName("original_name")
    val originalName: String, // Edward Norton
    @SerializedName("popularity")
    val popularity: Double, // 7.861
    @SerializedName("profile_path")
    val profilePath: String // /5XBzD5WuTyVQZeS4VI25z2moMeY.jpg
)