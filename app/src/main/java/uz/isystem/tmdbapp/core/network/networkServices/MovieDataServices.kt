package uz.isystem.tmdbapp.core.network.networkServices

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.isystem.tmdbapp.core.models.response.main.home.cast.CastResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails.MovieDetailsResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieImage.MovieImagesResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieTrailer.MovieTrailerResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse

interface MovieDataServices {

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<MovieDetailsResponse?>>

    ///movie/{movie_id}/similar
    @GET("/3/movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<Response<SimilarMoviesResponse?>>

    ///movie/{movie_id}/images
    @GET("/3/movie/{movie_id}/images")
    fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<MovieImagesResponse?>>

    @GET("/3/movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<MovieTrailerResponse?>>

    @GET("/3/movie/{movie_id}/credits")
    fun getCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<Response<CastResponse?>>


}