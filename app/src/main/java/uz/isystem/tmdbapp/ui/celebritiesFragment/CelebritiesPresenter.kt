package com.example.movieapp.ui.main.home.searchFragment

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.BuildConfig
import uz.isystem.tmdbapp.core.models.response.main.home.popularPeople.PeoplePopularResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.ActorDataServices

class CelebritiesPresenter(val view: CelebritiesMVP.View) : CelebritiesMVP.Presenter {

    var actorDataServices: ActorDataServices = ApiClientModule.getActorData()
    var compositeDisposable = CompositeDisposable()

    override fun loadCelebritiesCast() {
        var disposable = actorDataServices.getPersonPopular(apiKey = BuildConfig.apiKey, page = 1)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<PeoplePopularResponse?>>() {
                    override fun onSuccess(t: Response<PeoplePopularResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.getCelebritiesCast(it)
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

    override fun loadCelebritiesTrending() {

        var disposable = actorDataServices.getTrendingPerson(
            apiKey = BuildConfig.apiKey,
            media_type = "person",
            time_window = "day",
            page = 1
        )
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<PeoplePopularResponse?>>() {
                    override fun onSuccess(t: Response<PeoplePopularResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.getCelebritiesTrending(it)
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