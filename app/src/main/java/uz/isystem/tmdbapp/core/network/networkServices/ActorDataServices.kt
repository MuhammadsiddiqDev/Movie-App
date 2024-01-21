package uz.isystem.tmdbapp.core.network.networkServices

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.isystem.tmdbapp.core.models.response.main.home.castSimilar.CastSimilarResponse
import uz.isystem.tmdbapp.core.models.response.main.home.detailActor.ActorDetailResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularPeople.PeoplePopularResponse

interface ActorDataServices {

    @GET("/3/person/{person_id}")
    fun getActorDetails(
        @Path("person_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<Response<ActorDetailResponse?>>

    @GET("/3/person/{person_id}/movie_credits")
    fun getSimilarMovies(
        @Path("person_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<Response<CastSimilarResponse?>>

    @GET("/3/person/popular")
    fun getPersonPopular(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Single<Response<PeoplePopularResponse?>>

    @GET("/3/trending/{media_type}/{time_window}")
    fun getTrendingPerson(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Single<Response<PeoplePopularResponse?>>

}