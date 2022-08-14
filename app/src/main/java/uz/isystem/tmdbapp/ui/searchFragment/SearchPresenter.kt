package com.example.movieapp.ui.main.home.searchFragment

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.BuildConfig
import uz.isystem.tmdbapp.core.models.response.main.home.search.SearchResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.SearchService

class SearchPresenter(val view: SearchMVP.View) : SearchMVP.Presenter {

    var searchService: SearchService = ApiClientModule.getSearchQueryService()
    var compositeDisposable = CompositeDisposable()

    override fun loadSearchResult(query: String) {
        var disposable = searchService.searchMovie(apiKey = BuildConfig.apiKey, query = query)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<SearchResponse?>>() {
                    override fun onSuccess(t: Response<SearchResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.getSearchResult(it.results)
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