package uz.isystem.tmdbapp.ui.login

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.isystem.tmdbapp.BuildConfig
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.request.createSession.RequestCreateSession
import uz.isystem.tmdbapp.core.models.request.createSessionWithLogin.RequestCreateSessionWIthLogin
import uz.isystem.tmdbapp.core.models.response.login.acountDetail.AccountDetailResponse
import uz.isystem.tmdbapp.core.models.response.login.createRequestToken.CreateRequestTokenResponse
import uz.isystem.tmdbapp.core.models.response.login.createSession.CreateSessionResponse
import uz.isystem.tmdbapp.core.network.ApiClientModule
import uz.isystem.tmdbapp.core.network.networkServices.LoginServices

class LoginPresenter(val view: LoginMVP.View) : LoginMVP.Presenter {
    var loginServices: LoginServices = ApiClientModule.getLoginService()

    private val compositeDisposable = CompositeDisposable()

    override fun createRequestToken() {
        val disposable = loginServices.createRequestToken(ApiClientModule.apiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object :
                DisposableSingleObserver<Response<CreateRequestTokenResponse?>>() {
                override fun onSuccess(t: Response<CreateRequestTokenResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            AppCache.appCache!!.setAccessToken(it.requestToken)
                            view.createRequestToken(it.requestToken)
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

    override fun createSessionWithLogin(username: String, password: String) {

        val body = RequestCreateSessionWIthLogin(
            username = username,
            password = password,
            AppCache.appCache!!.getAccessToken()
        )
        val disposable = loginServices.createSessionWithLogin(
            BuildConfig.apiKey,
            requestCreateSessionWIthLogin = body
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object :
                DisposableSingleObserver<Response<CreateRequestTokenResponse?>>() {
                override fun onSuccess(t: Response<CreateRequestTokenResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            AppCache.appCache!!.setAccessToken(it.requestToken)
                            view.createSessionWithLogin(it)
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

    override fun createSession() {
        val body = RequestCreateSession(AppCache.appCache!!.getAccessToken())
        val disposable = loginServices.createSession(
            BuildConfig.apiKey,
            requestCreateSession = body
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object :
                DisposableSingleObserver<Response<CreateSessionResponse?>>() {
                override fun onSuccess(t: Response<CreateSessionResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            AppCache.appCache!!.setUserSession(it.sessionId)
                            view.createSession(it.sessionId)
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

    override fun getAccountDetail() {
        val disposable = loginServices.getAccountDetail(
            BuildConfig.apiKey,
            sessionId = AppCache.appCache!!.getUserSession()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<Response<AccountDetailResponse?>>() {
                override fun onSuccess(t: Response<AccountDetailResponse?>) {
                    if (t.isSuccessful) {
                        t.body()?.let {
                            view.accountDetail(it)
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