package uz.isystem.tmdbapp.core.app

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber
import uz.isystem.tmdbapp.BuildConfig
import uz.isystem.tmdbapp.core.cache.AppCache
import uz.isystem.tmdbapp.core.locale.LocaleManager

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

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { LocaleManager.setLang(it) })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLang(this)
    }

    companion object {
        var instance: Application? = null
            private set
    }


}