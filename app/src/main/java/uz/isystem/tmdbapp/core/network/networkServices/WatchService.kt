package uz.isystem.tmdbapp.core.network.networkServices

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.isystem.tmdbapp.core.models.response.main.home.watch.WatchResponse

interface WatchService {

    @GET("/3/account/{account_id}/watchlist/movies")
    fun watchListMovie(
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Single<Response<WatchResponse?>>

}