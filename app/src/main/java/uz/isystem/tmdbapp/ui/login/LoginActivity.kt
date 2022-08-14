package uz.isystem.tmdbapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.models.response.login.acountDetail.AccountDetailResponse
import uz.isystem.tmdbapp.core.models.response.login.createRequestToken.CreateRequestTokenResponse
import uz.isystem.tmdbapp.databinding.ActivityLoginBinding
import uz.isystem.tmdbapp.ui.base.BaseActivity
import uz.isystem.tmdbapp.ui.main.MainActivity

class LoginActivity : BaseActivity(), LoginMVP.View {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    companion object {
        var ISFIRST = true
    }

    private lateinit var presenter: LoginMVP.Presenter

    override fun getView(): View? {
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        presenter = LoginPresenter(this)


        binding.singIn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE

            binding.editPassword.isVisible
            binding.editUserName.isVisible
            binding.nextButton.isClickable = false
            binding.singIn.isClickable = false

            presenter.createRequestToken()
        }
        binding.singUp.movementMethod = LinkMovementMethod.getInstance()

        binding.nextButton.setOnClickListener {
            val startIntent = Intent(this, MainActivity::class.java)
            startActivity(startIntent)
            finish()
        }
    }

    override fun isLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    override fun setData(any: Any) {
    }

    override fun createRequestToken(token: String) {

        val login = binding.editUserName.text.toString()
        val password = binding.editPassword.text.toString()
        presenter.createSessionWithLogin(login, password)
        binding.progressBar.visibility
    }

    override fun createSessionWithLogin(response: CreateRequestTokenResponse) {
        presenter.createSession()
    }

    override fun createSession(sessionId: String) {
        presenter.getAccountDetail()
    }

    override fun accountDetail(accountDetail: AccountDetailResponse) {
        AppCache.appCache!!.setFirstOpen(false)
        val startIntent = Intent(this, MainActivity::class.java)
        startActivity(startIntent)
        finish()
    }

    override fun onError(message: String?) {

        binding.progressBar.visibility = View.INVISIBLE
        binding.editPassword.visibility
        binding.editUserName.visibility
        binding.nextButton.isClickable = true
        binding.singIn.isClickable = true


        val builder = AlertDialog.Builder(this)
        builder.setMessage("We have a message")
    }

    override fun onStop() {
        super.onStop()
        presenter.cancelRequest()
    }
}