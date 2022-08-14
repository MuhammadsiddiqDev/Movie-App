package uz.isystem.tmdbapp.ui.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import uz.isystem.tmdbapp.ui.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun getView(): View? {
        return null
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        startActivity(Intent(this, StartActivity::class.java))
        finish()
    }

}