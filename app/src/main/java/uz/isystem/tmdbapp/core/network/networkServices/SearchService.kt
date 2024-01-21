package uz.isystem.tmdbapp.core.network.networkServices

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.tmdbapp.core.models.response.main.home.search.actior.SearchActorResponse
import uz.isystem.tmdbapp.core.models.response.main.home.search.movie.SearchResponse

interface SearchService {

    @GET("/3/search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Single<Response<SearchResponse?>>

    @GET("/3/search/person")
    fun searchActor(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Single<Response<SearchActorResponse?>>

}