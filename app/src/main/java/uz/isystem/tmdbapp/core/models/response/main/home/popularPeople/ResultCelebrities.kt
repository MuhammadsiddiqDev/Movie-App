package uz.isystem.tmdbapp.core.models.response.main.home.popularPeople


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ResultCelebrities(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("id")
    val id: Int, // 28782
    @SerializedName("known_for")
    val knownFor: List<KnownFor>,
    @SerializedName("name")
    val name: String, // Monica Bellucci
    @SerializedName("popularity")
    val popularity: Double, // 48.609344
    @SerializedName("profile_path")
    val profilePath: String // /z3sLuRKP7hQVrvSTsqdLjGSldwG.jpg
)