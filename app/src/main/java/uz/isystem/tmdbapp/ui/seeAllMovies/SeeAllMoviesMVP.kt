package com.example.movieapp.ui.main.seeAllMovies

import uz.isystem.tmdbapp.core.models.response.main.home.UpcomingMovie.UpcomingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.castSimilar.CastSimilarResponse
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.NowPlayingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularMovie.PopularResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse
import uz.isystem.tmdbapp.core.models.response.main.home.topRatedMovie.TopRatedResponse

interface SeeAllMoviesMVP {

    interface View {
        fun setPopularMovies(popularResponse: PopularResponse)
        fun setNowPlayingMovies(nowPlayingResponse: NowPlayingResponse)
        fun setTopRatedMovies(topRatedResponse: TopRatedResponse)
        fun setUpcomingMovies(upcomingResponse: UpcomingResponse)
        fun setSimilarMovies(similarMoviesResponse: SimilarMoviesResponse)
        fun setSimilarActor(castSimilarResponse: CastSimilarResponse)
        fun onError(message: String)
    }

    interface Presenter {
        fun loadPopularMovies(language: String, page: Int)
        fun loadNowPlayingMovies(language: String, page: Int)
        fun loadTopRatedMovies(language: String, page: Int)
        fun loadUpcomingMovies(language: String, page: Int)
        fun loadSimilarMovies(movieId: Int, language: String, page: Int)
        fun cancelRequest()
        fun loadSimilarActor(movie: Int, language: String)
    }

}