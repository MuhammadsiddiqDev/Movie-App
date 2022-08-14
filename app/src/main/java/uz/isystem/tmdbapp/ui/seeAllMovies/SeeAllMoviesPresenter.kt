package com.example.movieapp.ui.main.seeAllMovies

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.core.models.response.main.home.UpcomingMovie.UpcomingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.castSimilar.CastSimilarResponse
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.NowPlayingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularMovie.PopularResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse
import uz.isystem.tmdbapp.core.models.response.main.home.topRatedMovie.TopRatedResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.ActorDataServices

class SeeAllMoviesPresenter(val view: SeeAllMoviesMVP.View) : SeeAllMoviesMVP.Presenter {

    var movieServer = ApiClientModule.getMovieService()
    var movieServerDetails = ApiClientModule.getMoviesData()
    var actorDataServices: ActorDataServices = ApiClientModule.getActorData()
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadPopularMovies() {
        var disposable = movieServer.getPopularMovies(ApiClientModule.apiKey, 2)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<PopularResponse?>>() {
                    override fun onSuccess(t: Response<PopularResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setPopularMovies(it)
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

    override fun loadNowPlayingMovies() {
        val disposable = movieServer.getNowPlayingMovies(ApiClientModule.apiKey, 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object :
                DisposableSingleObserver<Response<NowPlayingResponse?>>() {
                override fun onSuccess(t: Response<NowPlayingResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setNowPlayingMovies(it)
                        }
                    } else {
                        view.onError(t.message())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message.toString())
                }

            })

        compositeDisposable.add(disposable)
    }

    override fun loadTopRatedMovies() {
        var disposable = movieServer.getTopRatedMovies(ApiClientModule.apiKey, 1)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<TopRatedResponse?>>() {
                    override fun onSuccess(t: Response<TopRatedResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setTopRatedMovies(it)
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

    override fun loadUpcomingMovies() {
        var disposable = movieServer.getUpcomingMovies(ApiClientModule.apiKey, 1)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<UpcomingResponse?>>() {
                    override fun onSuccess(t: Response<UpcomingResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setUpcomingMovies(it)
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

    override fun loadSimilarMovies(movieId: Int) {
        var disposable = movieServerDetails.getSimilarMovies(
            apiKey = ApiClientModule.apiKey,
            page = 1,
            movieId = movieId
        )
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<SimilarMoviesResponse?>>() {
                    override fun onSuccess(t: Response<SimilarMoviesResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setSimilarMovies(it)
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


    override fun loadSimilarActor(movieId: Int) {
        var disposable = actorDataServices.getSimilarMovies(
            movieId = movieId,
            apiKey = ApiClientModule.apiKey,
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<CastSimilarResponse?>>() {
                override fun onSuccess(t: Response<CastSimilarResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setSimilarActor(it)
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