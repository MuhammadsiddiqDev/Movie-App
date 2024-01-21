package uz.isystem.tmdbapp.ui.movieDetails

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.BuildConfig
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.request.watch.AddWatchRequest
import uz.isystem.tmdbapp.core.models.response.main.home.cast.CastResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieDetails.MovieDetailsResponse
import uz.isystem.tmdbapp.core.models.response.main.home.movieData.movieTrailer.MovieTrailerResponse
import uz.isystem.tmdbapp.core.models.response.main.home.similarMovies.SimilarMoviesResponse
import uz.isystem.tmdbapp.core.models.response.main.home.watch.AddWatchResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.MovieDataServices

class MovieDetailsPresenter(val view: MovieDetailsMVP.View) : MovieDetailsMVP.Presenter {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var movieDetailsServer: MovieDataServices = ApiClientModule.getMoviesData()


    override fun loadMovieDetails(movieId: Int, language: String) {
        var disposable =
            movieDetailsServer.getMovieDetails(
                apiKey = ApiClientModule.apiKey,
                movieId = movieId,
                language = language
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object :
                    DisposableSingleObserver<Response<MovieDetailsResponse?>>() {
                    override fun onSuccess(t: Response<MovieDetailsResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setMovieDetails(it)
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
        var disposable = movieDetailsServer.getSimilarMovies(
            movieId = movieId,
            apiKey = ApiClientModule.apiKey,
            page = 1,
            language = language
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<SimilarMoviesResponse?>>() {
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
                    view.onError(e.toString())

                }

            })
        compositeDisposable.add(disposable)
    }

    override fun loadMovieTrailer(movieId: Int, language: String) {
        var disposable = movieDetailsServer.getMovieTrailer(
            movieId = movieId,
            apiKey = ApiClientModule.apiKey,
            language = language
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<MovieTrailerResponse?>>() {
                override fun onSuccess(t: Response<MovieTrailerResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setTrailerMovies(it)
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

    override fun loadMovieCast(movieId: Int, language: String) {
        var disposable =
            movieDetailsServer.getCast(
                apiKey = ApiClientModule.apiKey,
                movieId = movieId,
                language = language
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object :
                    DisposableSingleObserver<Response<CastResponse?>>() {
                    override fun onSuccess(t: Response<CastResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setMovieCast(it)
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


    override fun markAsFavorite(movieId: Int, language: String) {

    }
//
//    override fun markAsFavorite(movieId: Int) {
//        val body = MarkAsFavoriteRequest(true, media_id = movieId, "movie")
//        var disposable = movieDetailsServer.markAsFavorite(
//            accountId = AppCache.appCache!!.getAccountId(),
//            apiKey = ApiClientModule.apiKey,
//            sessionId = AppCache.appCache!!.getSessionId(),
//            markAsFavoriteRequest = body
//        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
//            object : DisposableSingleObserver<Response<MarkAsFavoriteResponse?>>() {
//                override fun onSuccess(t: Response<MarkAsFavoriteResponse?>) {
//                    if (t.isSuccessful) {
//                        t.body()?.let {
//                            view.getFavoriteResult(true)
//                        }
//                    } else {
//                        view.getFavoriteResult(false)
//                    }
//                }
//
//                override fun onError(e: Throwable) {
//                    view.onError(e.message.toString())
//                }
//
//            })
//        compositeDisposable.add(disposable)
//    }


    override fun cancelRequest() {
        compositeDisposable.dispose()
    }

    override fun addSaved(add: Boolean, movieId: Int) {
        var body = AddWatchRequest(
            mediaId = movieId,
            mediaType = "movie",
            watchlist = add
        )

        var disposable = movieDetailsServer.addWatch(
            apiKey = BuildConfig.apiKey,
            sessionId = AppCache.appCache!!.getUserSession(),
            addWatchRequest = body
        )
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<AddWatchResponse?>>() {
                    override fun onSuccess(t: Response<AddWatchResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.getAddSaved(it)
                            }
                        } else {
                            view.onError(t.message().toString())
                        }
                    }


                    override fun onError(e: Throwable) {
                        view.onError(e.message.toString())
                    }

                })
    }

}
