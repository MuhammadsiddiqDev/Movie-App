package uz.isystem.tmdbapp.ui.base

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import uz.fozilbekimomov.lesson44movieapp.core.extensions.SetItemStatusBarColor
import uz.isystem.tmdbapp.R

open abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getView()?.let {
            setContentView(it)
        }
        onCreated(savedInstanceState)

        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        SetItemStatusBarColor(getColor(R.color.white), true)

    }

    abstract fun getView(): View?

    abstract fun onCreated(savedInstanceState: Bundle?)
}