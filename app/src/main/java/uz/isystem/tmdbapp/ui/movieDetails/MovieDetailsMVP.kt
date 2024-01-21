package uz.isystem.tmdbapp.ui.movieDetails

import uz.isystem.tmdbapp.core.models.response.main.home.cast.CastResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails.MovieDetailsResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieTrailer.MovieTrailerResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse
import uz.isystem.tmdbapp.core.models.response.main.home.watch.AddWatchResponse

interface MovieDetailsMVP {

    interface View {

        fun setMovieDetails(movieDetails: MovieDetailsResponse)

        fun setMovieCast(castResponse: CastResponse)

        fun setSimilarMovies(similarMoviesResponse: SimilarMoviesResponse)

        fun setTrailerMovies(movieTrailerResponse: MovieTrailerResponse)

        fun getAddSaved(addWatchResponse: AddWatchResponse)

        fun getFavoriteResult(success: Boolean)

        fun onError(message: String)

    }

    interface Presenter {

        fun cancelRequest()

        fun addSaved(add: Boolean, movieId: Int)

        fun loadMovieDetails(movieId: Int, language: String)

        fun loadMovieCast(movieId: Int, language: String)

        fun loadSimilarMovies(movieId: Int, language: String)

        fun loadMovieTrailer(movieId: Int, language: String)

        fun markAsFavorite(movieId: Int, language: String)

    }

}