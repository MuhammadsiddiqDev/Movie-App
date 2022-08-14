package uz.isystem.tmdbapp.core.network.networkServices

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.isystem.tmdbapp.core.models.response.main.home.UpcomingMovie.UpcomingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.NowPlayingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularMovie.PopularResponse
import uz.isystem.tmdbapp.core.models.response.main.home.sliderMovie.SliderResponse
import uz.isystem.tmdbapp.core.models.response.main.home.topRatedMovie.TopRatedResponse

interface MovieServices {

    @GET("/3/trending/{media_type}/{time_window}")
    fun getSliderMovies(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<SliderResponse?>>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<TopRatedResponse?>>


    @GET("/3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<PopularResponse?>>


    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<NowPlayingResponse?>>

    @GET("/3/movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<UpcomingResponse?>>


}