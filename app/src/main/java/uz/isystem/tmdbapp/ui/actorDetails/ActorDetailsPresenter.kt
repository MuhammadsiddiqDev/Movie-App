package com.example.movieapp.ui.main.movieDetails

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.core.models.response.main.home.castSimilar.CastSimilarResponse
import uz.isystem.tmdbapp.core.models.response.main.home.detailActor.ActorDetailResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.ActorDataServices

class ActorDetailsPresenter(val view: ActorDetailsMVP.View) : ActorDetailsMVP.Presenter {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var actorDataServices: ActorDataServices = ApiClientModule.getActorData()


    override fun loadActorDetails(movieId: Int, language: String) {
        var disposable =
            actorDataServices.getActorDetails(
                apiKey = ApiClientModule.apiKey,
                movieId = movieId,
                language = language
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object :
                    DisposableSingleObserver<Response<ActorDetailResponse?>>() {
                    override fun onSuccess(t: Response<ActorDetailResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setActorDetails(it)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        view.onError(e.message.toString())
                    }

                })

        compositeDisposable.add(disposable)

    }

    override fun loadSimilarMovies(movieId: Int, language: String) {
        var disposable = actorDataServices.getSimilarMovies(
            movieId = movieId,
            apiKey = ApiClientModule.apiKey,
            language = language
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<CastSimilarResponse?>>() {
                override fun onSuccess(t: Response<CastSimilarResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setSimilarMovies(it)
                        }
                    } else {
                        view.onError(t.message().toString())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.toString())

                }

            })
        compositeDisposable.add(disposable)
    }

    override fun cancelRequest() {
        compositeDisposable.dispose()
    }

}
