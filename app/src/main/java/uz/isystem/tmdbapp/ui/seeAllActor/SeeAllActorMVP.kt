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

        fun loadActorTrending()
        fun loadActorPopular()
        fun loadActorDetailSimilar(movieId: Int)
        fun cancelRequest()
    }

}