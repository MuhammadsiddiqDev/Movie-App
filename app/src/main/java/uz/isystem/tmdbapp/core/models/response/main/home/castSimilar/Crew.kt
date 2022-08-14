package uz.isystem.tmdbapp.core.models.response.main.home.castSimilar


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Crew(
    @SerializedName("adult")
    val adult: Boolean, // false
    @SerializedName("backdrop_path")
    val backdropPath: String, // /xnRPoFI7wzOYviw3PmoG94X2Lnc.jpg
    @SerializedName("credit_id")
    val creditId: String, // 52fe492cc3a368484e11dfe1
    @SerializedName("department")
    val department: String, // Production
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int, // 76203
    @SerializedName("job")
    val job: String, // Producer
    @SerializedName("original_language")
    val originalLanguage: String, // en
    @SerializedName("original_title")
    val originalTitle: String, // 12 Years a Slave
    @SerializedName("overview")
    val overview: String, // In the pre-Civil War United States, Solomon Northup, a free black man from upstate New York, is abducted and sold into slavery. Facing cruelty as well as unexpected kindnesses Solomon struggles not only to stay alive, but to retain his dignity. In the twelfth year of his unforgettable odyssey, Solomon’s chance meeting with a Canadian abolitionist will forever alter his life.
    @SerializedName("popularity")
    val popularity: Double, // 6.62674
    @SerializedName("poster_path")
    val posterPath: String, // /kb3X943WMIJYVg4SOAyK0pmWL5D.jpg
    @SerializedName("release_date")
    val releaseDate: String, // 2013-10-18
    @SerializedName("title")
    val title: String, // 12 Years a Slave
    @SerializedName("video")
    val video: Boolean, // false
    @SerializedName("vote_average")
    val voteAverage: Double, // 7.9
    @SerializedName("vote_count")
    val voteCount: Int // 3284
)