package com.example.movieapp.ui.main.seeAllMovies

import uz.isystem.tmdbapp.core.models.response.main.home.cast.CastResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularPeople.PeoplePopularResponse

interface SeeAllActorMVP {

    interface View {

        fun setActorTrending(castResponse: CastResponse)
        fun setActorPopular(popularResponse: PeoplePopularResponse)
        fun setActorDetailSimilar(popularResponse: PeoplePopularResponse)
        fun onError(message: String)
    }

    interface Presenter {

        fun loadActorTrending(language: String, page: Int)
        fun loadActorPopular(language: String, page: Int)
        fun loadActorDetailSimilar(movieId: Int, language: String)
        fun cancelRequest()
    }

}