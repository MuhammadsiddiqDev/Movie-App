package uz.isystem.tmdbapp.ui.main.home

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.core.models.response.main.home.UpcomingMovie.UpcomingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.nowPlayingMovie.NowPlayingResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularMovie.PopularResponse
import uz.isystem.tmdbapp.core.models.response.main.home.sliderMovie.SliderResponse
import uz.isystem.tmdbapp.core.models.response.main.home.topRatedMovie.TopRatedResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.MovieServices

class HomePresenter(val view: HomeMVP.View) : HomeMVP.Presenter {

    var movieService: MovieServices = ApiClientModule.getMovieService()

    private val compositeDisposable = CompositeDisposable()
    override fun loadSliderData(language: String) {
        val disposable = movieService.getSliderMovies(
            apiKey = ApiClientModule.apiKey,
            media_type = "movie",
            time_window = "day",
            page = 1,
            language = language
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object :
                DisposableSingleObserver<Response<SliderResponse?>>() {
                override fun onSuccess(t: Response<SliderResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setMovieData(it)
                        }
                    } else {
                        view.onError(t.message())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message)
                }

            })

        compositeDisposable.add(disposable)
    }

    override fun loadTopRated(language: String) {
        val disposable =
            movieService.getTopRatedMovies(ApiClientModule.apiKey, 1, language = language)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object :
                    DisposableSingleObserver<Response<TopRatedResponse?>>() {
                    override fun onSuccess(t: Response<TopRatedResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setMovieData(it)
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

    override fun loadPopular(language: String) {
        val disposable =
            movieService.getPopularMovies(ApiClientModule.apiKey, 1, language = language)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object :
                    DisposableSingleObserver<Response<PopularResponse?>>() {
                    override fun onSuccess(t: Response<PopularResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setMovieData(it)
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

    override fun loadNowPlaying(language: String) {
        val disposable =
            movieService.getNowPlayingMovies(ApiClientModule.apiKey, 1, language = language)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object :
                    DisposableSingleObserver<Response<NowPlayingResponse?>>() {
                    override fun onSuccess(t: Response<NowPlayingResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setMovieData(it)
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

    override fun loadUpcomingData(language: String) {
        val disposable =
            movieService.getUpcomingMovies(ApiClientModule.apiKey, 1, language = language)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object :
                    DisposableSingleObserver<Response<UpcomingResponse?>>() {
                    override fun onSuccess(t: Response<UpcomingResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setMovieData(it)
                            }
                        } else {
                        view.onError(t.message())
                    }
                }

                override fun onError(e: Throwable) {
                    view.onError(e.message)
                }

            })

        compositeDisposable.add(disposable)
    }


    override fun cancelRequest() {
        compositeDisposable.dispose()
    }
}