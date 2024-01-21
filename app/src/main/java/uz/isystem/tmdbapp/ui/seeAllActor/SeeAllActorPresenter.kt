package uz.isystem.tmdbapp.ui.seeAllActor

import com.example.movieapp.ui.main.seeAllMovies.SeeAllActorMVP
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.core.models.response.main.home.cast.CastResponse
import uz.isystem.tmdbapp.core.models.response.main.home.popularPeople.PeoplePopularResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.ActorDataServices
import uz.isystem.tmdbapp.core.network.networkServices.MovieDataServices

class SeeAllActorPresenter(val view: SeeAllActorMVP.View) : SeeAllActorMVP.Presenter {

    var actorDataServices: ActorDataServices = ApiClientModule.getActorData()
    var actorCast: MovieDataServices = ApiClientModule.getMoviesData()
    var compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun loadActorDetailSimilar(movieId: Int, language: String) {
        var disposable = actorCast.getCast(
            apiKey = ApiClientModule.apiKey,
            movieId = movieId,
            language = language
        )
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeWith(
                object : DisposableSingleObserver<Response<CastResponse?>>() {
                    override fun onSuccess(t: Response<CastResponse?>) {
                        if (t.isSuccessful) {
                            t.body()?.let {
                                view.setActorTrending(it)
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

    override fun loadActorPopular(language: String, page: Int) {
        var disposable =
            actorDataServices.getPersonPopular(
                apiKey = ApiClientModule.apiKey,
                page = page,
                language = language
            )
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribeWith(
                    object : DisposableSingleObserver<Response<PeoplePopularResponse?>>() {
                        override fun onSuccess(t: Response<PeoplePopularResponse?>) {
                            if (t.isSuccessful) {
                                t.body()?.let {
                                    view.setActorPopular(it)
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


    override fun loadActorTrending(language: String, page: Int) {
        var disposable = actorDataServices.getTrendingPerson(
            apiKey = ApiClientModule.apiKey,
            media_type = "person",
            time_window = "day",
            page = page,
            language = language
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<PeoplePopularResponse?>>() {
                override fun onSuccess(t: Response<PeoplePopularResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.setActorDetailSimilar(it)
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