package uz.isystem.tmdbapp.ui.login

import uz.isystem.tmdbapp.core.models.response.login.acountDetail.AccountDetailResponse
import uz.isystem.tmdbapp.core.models.response.login.createRequestToken.CreateRequestTokenResponse

interface LoginMVP {
    interface View {
        fun isLoading(isLoading: Boolean)
        fun setData(any: Any)
        fun createRequestToken(token: String)
        fun createSessionWithLogin(response: CreateRequestTokenResponse)
        fun createSession(sessionId: String)
        fun accountDetail(accountDetail: AccountDetailResponse)
        fun onError(message: String?)
    }

    interface Presenter {
        fun createRequestToken()
        fun createSessionWithLogin(username: String, password: String)
        fun createSession()
        fun getAccountDetail()
        fun cancelRequest()
    }
}
