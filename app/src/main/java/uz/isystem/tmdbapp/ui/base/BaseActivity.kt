package uz.isystem.tmdbapp.ui.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import uz.fozilbekimomov.lesson44movieapp.core.extensions.SetItemStatusBarColor
import uz.isystem.tmdbapp.R
import uz.isystem.tmdbapp.core.locale.LocaleManager

open abstract class BaseActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
//        }

        LocaleManager.setLang(this)

        super.onCreate(savedInstanceState)
        getView()?.let {
            setContentView(it)
        }
        onCreated(savedInstanceState)


        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        SetItemStatusBarColor(getColor(R.color.black), true)

    }


    abstract fun getView(): View?

    abstract fun onCreated(savedInstanceState: Bundle?)


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { LocaleManager.setLang(it) })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        recreate()
        super.onConfigurationChanged(newConfig)
    }


}