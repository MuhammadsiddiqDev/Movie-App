package uz.isystem.tmdbapp.core.locale

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import uz.isystem.tmdbapp.core.cache.AppCache
import java.util.Locale

object LocaleManager {

    fun setLang(context: Context): Context {

        AppCache.init(context)

        val currentLang: String? = AppCache.appCache?.getLanguage()

        return updateRecourse(context, currentLang)

    }

    private fun updateRecourse(context: Context, currentLang: String?): Context {

        val locale: Locale = Locale(currentLang)

        Locale.setDefault(locale)

        val resources: Resources = context.resources

        val configuration: Configuration = resources.configuration

        configuration.setLocale(locale)

        context.createConfigurationContext(configuration)

        return context
    }

}