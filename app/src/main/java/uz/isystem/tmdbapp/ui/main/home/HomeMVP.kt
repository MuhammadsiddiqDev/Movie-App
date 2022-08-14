package uz.isystem.tmdbapp.ui.main.home

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

        fun loadSliderData()

        fun loadTopRated()

        fun loadPopular()

        fun loadNowPlaying()

        fun loadUpcomingData()

        fun cancelRequest()
    }
}