package uz.isystem.tmdbapp.core.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber
import uz.isystem.tmdbapp.BuildConfig
import uz.isystem.tmdbapp.core.cache.AppCache

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCache.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        var instance: Application? = null
            private set
    }
}