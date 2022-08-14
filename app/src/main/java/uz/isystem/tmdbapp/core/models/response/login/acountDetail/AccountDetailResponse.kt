package uz.isystem.tmdbapp.core.models.response.login.acountDetail


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AccountDetailResponse(
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("id")
    val id: Int,
    @SerializedName("iso_639_1")
    val iso6391: String, // en
    @SerializedName("iso_3166_1")
    val iso31661: String, // US
    @SerializedName("name")
    val name: String,
    @SerializedName("include_adult")
    val includeAdult: Boolean,
    @SerializedName("username")
    val username: String
)