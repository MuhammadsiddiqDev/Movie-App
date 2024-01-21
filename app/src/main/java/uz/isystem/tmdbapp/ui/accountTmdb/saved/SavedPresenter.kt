package uz.isystem.tmdbapp.ui.accountTmdb.saved

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.BuildConfig
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.main.home.watch.WatchResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.WatchService

class SavedPresenter(val view: SavedMVP.View) : SavedMVP.Presenter {

    var savedService: WatchService = ApiClientModule.getWatchService()
    var compositeDisposable = CompositeDisposable()

    override fun loadSavedResult(language: String, page: Int) {
        var disposable = savedService.watchListMovie(
            apiKey = BuildConfig.apiKey,
            sessionId = AppCache.appCache!!.getUserSession(),
            language = language,
            page = page
        )
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<WatchResponse?>>() {
                    override fun onSuccess(t: Response<WatchResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.getSavedResult(it)
                            }
                        } else {
                            view.onError(t.message().toString())
                        }
                    }


                    override fun onError(e: Throwable) {
                        view.onError(e.message.toString())
                    }

                })
        compositeDisposable.add(disposable)
    }


    override fun cancelRequest() {
        compositeDisposable.dispose()
    }

}