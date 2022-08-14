package uz.isystem.tmdbapp.ui.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.databinding.ActivityStartBinding
import uz.isystem.tmdbapp.ui.base.BaseActivity
import uz.isystem.tmdbapp.ui.login.LoginActivity
import uz.isystem.tmdbapp.ui.main.MainActivity

class StartActivity : BaseActivity() {
    private var _binding: ActivityStartBinding? = null
    private val binding get() = _binding!!

    override fun getView(): View? {
        _binding = ActivityStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreated(savedInstanceState: Bundle?) {

        if (AppCache.appCache!!.isFirstOpen()) {

            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)

            finish()
        } else {
            val loginIntent = Intent(this, MainActivity::class.java)
            startActivity(loginIntent)

            finish()
        }

    }
}