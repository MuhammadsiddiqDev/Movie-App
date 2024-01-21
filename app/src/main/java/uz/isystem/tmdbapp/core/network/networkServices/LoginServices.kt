package uz.isystem.tmdbapp.core.network.networkServices

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uz.isystem.tmdbapp.core.models.request.createSession.RequestCreateSession
import uz.isystem.tmdbapp.core.models.request.createSessionWithLogin.RequestCreateSessionWIthLogin
import uz.isystem.tmdbapp.core.models.response.login.acountDetail.AccountDetailResponse
import uz.isystem.tmdbapp.core.models.response.login.createRequestToken.CreateRequestTokenResponse
import uz.isystem.tmdbapp.core.models.response.login.createSession.CreateSessionResponse

interface LoginServices {
    //  1
    @GET("/3/authentication/token/new")
    fun createRequestToken(@Query("api_key") apiKey: String): Single<Response<CreateRequestTokenResponse?>>

    //  2
    @POST("/3/authentication/token/validate_with_login")
    fun createSessionWithLogin(
        @Query("api_key") apiKey: String,
        @Body requestCreateSessionWIthLogin: RequestCreateSessionWIthLogin
    ): Single<Response<CreateRequestTokenResponse?>>

    //  3
    @POST("/3/authentication/session/new")
    fun createSession(
        @Query("api_key") apiKey: String,
        @Body requestCreateSession: RequestCreateSession
    ): Single<Response<CreateSessionResponse?>>

    //  4
    @GET("/3/account")
    fun getAccountDetail(
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ): Single<Response<AccountDetailResponse?>>


}