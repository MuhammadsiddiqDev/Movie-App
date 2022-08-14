package com.example.movieapp.ui.main.movieDetails

import uz.isystem.tmdbapp.core.models.response.main.home.cast.CastResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails.MovieDetailsResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieTrailer.MovieTrailerResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse

interface MovieDetailsMVP {

    interface View {

        fun setMovieDetails(movieDetails: MovieDetailsResponse)

        fun setMovieCast(castResponse: CastResponse)

        fun setSimilarMovies(similarMoviesResponse: SimilarMoviesResponse)

        fun setTrailerMovies(movieTrailerResponse: MovieTrailerResponse)

        fun getFavoriteResult(success: Boolean)

        fun onError(message: String)

    }

    interface Presenter {

        fun cancelRequest()

        fun loadMovieDetails(movieId: Int)

        fun loadMovieCast(movieId: Int)

        fun loadSimilarMovies(movieId: Int)

        fun loadMovieTrailer(movieId: Int)

        fun markAsFavorite(movieId: Int)

    }

}