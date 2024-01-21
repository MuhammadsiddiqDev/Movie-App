package uz.isystem.tmdbapp.ui.accountTmdb.saved

import uz.isystem.tmdbapp.core.models.response.main.home.watch.WatchResponse

interface SavedMVP {

    interface View {
        fun getSavedResult(saveData: WatchResponse)
        fun onError(message: String)
    }

    interface Presenter {
        fun loadSavedResult(language: String, page: Int)
        fun cancelRequest()
    }
}