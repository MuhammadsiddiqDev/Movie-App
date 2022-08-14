package com.example.movieapp.ui.main.home.searchFragment

import uz.isystem.tmdbapp.core.models.response.main.home.search.SearchData

interface SearchMVP {

    interface View {
        fun getSearchResult(movieData: List<SearchData>)
        fun onError(message: String)
    }

    interface Presenter {
        fun loadSearchResult(query: String)
        fun cancelRequest()
    }
}