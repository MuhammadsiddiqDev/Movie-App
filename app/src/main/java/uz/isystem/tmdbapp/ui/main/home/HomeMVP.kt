package uz.isystem.tmdbapp.ui.main.home

import uz.isystem.tmdbapp.core.models.response.main.home.BaseData

interface HomeMVP {
    interface View {
        fun isLoading(isLoading: Boolean)
        fun onError(message: String?)

        fun setMovieData(data: BaseData)

//        fun setTopRated()
//        fun setTrailer()
//        fun setInTheatre()
    }

    interface Presenter {

        fun loadSliderData(language: String)

        fun loadTopRated(language: String)

        fun loadPopular(language: String)

        fun loadNowPlaying(language: String)

        fun loadUpcomingData(language: String)

        fun cancelRequest()
    }
}