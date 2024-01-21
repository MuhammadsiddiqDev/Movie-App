package uz.isystem.tmdbapp.ui.searchFragment

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.BuildConfig
import uz.isystem.tmdbapp.core.models.response.main.home.search.actior.SearchActorResponse
import uz.isystem.tmdbapp.core.models.response.main.home.search.movie.SearchResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.SearchService

class SearchPresenter(val view: SearchMVP.View) : SearchMVP.Presenter {

    var searchService: SearchService = ApiClientModule.getSearchQueryService()
    var compositeDisposable = CompositeDisposable()

    override fun loadSearchResult(query: String, language: String, page: Int) {
        var disposable = searchService.searchMovie(
            apiKey = BuildConfig.apiKey,
            query = query,
            language = language,
            page = page
        )
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<SearchResponse?>>() {
                    override fun onSuccess(t: Response<SearchResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.getSearchResult(it)
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

    override fun loadSearchActorResult(query: String, language: String, page: Int) {
        var disposable = searchService.searchActor(
            apiKey = BuildConfig.apiKey,
            query = query,
            language = language,
            page = page
        )
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<SearchActorResponse?>>() {
                    override fun onSuccess(t: Response<SearchActorResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.getSearchActorResult(it)
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