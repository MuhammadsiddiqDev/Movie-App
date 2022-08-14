package com.example.movieapp.ui.main.home.searchFragment

import uz.isystem.tmdbapp.core.models.response.main.home.popularPeople.PeoplePopularResponse

interface CelebritiesMVP {

    interface View {
        fun getCelebritiesCast(popularResponse: PeoplePopularResponse)
        fun getCelebritiesTrending(popularResponse: PeoplePopularResponse)

        fun onError(message: String)
    }

    interface Presenter {

        fun loadCelebritiesCast()
        fun loadCelebritiesTrending()
        fun cancelRequest()
    }
}