package uz.isystem.tmdbapp.ui.searchFragment

import uz.isystem.tmdbapp.core.models.response.main.home.search.actior.SearchActorResponse
import uz.isystem.tmdbapp.core.models.response.main.home.search.movie.SearchResponse

interface SearchMVP {

    interface View {
        fun getSearchResult(movieData: SearchResponse)
        fun getSearchActorResult(actorData: SearchActorResponse)
        fun onError(message: String)
    }

    interface Presenter {
        fun loadSearchResult(query: String, language: String, page: Int)
        fun loadSearchActorResult(query: String, language: String, page: Int)
        fun cancelRequest()
    }
}