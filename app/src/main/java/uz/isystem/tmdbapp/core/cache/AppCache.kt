package uz.isystem.tmdbapp.core.cache

import android.content.Context
import android.content.SharedPreferences

class AppCache private constructor(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("TMDb App", Context.MODE_PRIVATE)

    private var editor: SharedPreferences.Editor? = null
    private val IS_FIRST_OPEN = "isFirstOpen"
    private val USER_LOGIN = "isUserLogin"
    private val ACCESS_TOKEN = "userToken"
    private val USER_SESSION = "sessionId"
    private val LANGUAGE = "language"
    private val USERNAME = "username"
    private val NAME = "name"
    private val IMAGE = "image"


    companion object {
        var appCache: AppCache? = null
            private set

        fun init(context: Context) {
            if (appCache == null) {
                appCache = AppCache(context)
            }
        }
    }

    fun getLanguage(): String = preferences.getString(LANGUAGE, "en")!!

    fun setLanguage(language: String) {
        editor = preferences.edit()
        editor?.apply {
            putString(LANGUAGE, language.toString())
            apply()
        }
    }

    fun isUserLogin(): Boolean {
        return preferences.getBoolean(USER_LOGIN, false)
    }

    fun setUserLogin(isLogin: Boolean) {
        editor = preferences.edit()
        editor?.apply {
            putBoolean(USER_LOGIN, isLogin)
            apply()
        }
    }

    fun isFirstOpen(): Boolean {
        return preferences.getBoolean(IS_FIRST_OPEN, true)
    }

    fun setFirstOpen(isFirstOpen: Boolean) {
        editor = preferences.edit()
        editor?.apply {
            putBoolean(IS_FIRST_OPEN, isFirstOpen)
            apply()
        }
    }

    fun getUsername(): String = preferences.getString(USERNAME, "Username")!!

    fun setUsername(username: String) {
        editor = preferences.edit()
        editor?.apply {
            putString(USERNAME, username)
            apply()
        }
    }

    fun deleteUsername() {
        editor = preferences.edit()
        editor?.apply {
            remove(USERNAME)
            apply()
        }
    }

    fun getName(): String = preferences.getString(NAME, "Name")!!

    fun setName(name: String) {
        editor = preferences.edit()
        editor?.apply {
            putString(NAME, name)
            apply()
        }
    }

    fun deleteName() {
        editor = preferences.edit()
        editor?.apply {
            remove(NAME)
            apply()
        }
    }

    fun getImage(): String = preferences.getString(
        IMAGE,
        "https://c8.alamy.com/zooms/9/80d94c5b96c54446b2dc609a62b9f61b/2c5xkmf.jpg"
    )!!

    fun setImage(image: String) {
        editor = preferences.edit()
        editor?.apply {
            putString(IMAGE, image)
            apply()
        }
    }

    fun deleteImage() {
        editor = preferences.edit()
        editor?.apply {
            remove(IMAGE)
            apply()
        }
    }

    fun getAccessToken(): String = preferences.getString(ACCESS_TOKEN, "Hello_app")!!

    fun setAccessToken(token: String) {
        editor = preferences.edit()
        editor?.apply {
            putString(ACCESS_TOKEN, token)
            apply()
        }
    }

    fun deleteToken() {
        editor = preferences.edit()
        editor?.apply {
            remove(ACCESS_TOKEN)
            apply()
        }
    }

    fun getUserSession(): String = preferences.getString(USER_SESSION, "Hello_app")!!

    fun setUserSession(sessionId: String) {
        editor = preferences.edit()
        editor?.apply {
            putString(USER_SESSION, sessionId)
            apply()
        }
    }

    fun deleteSession() {
        editor = preferences.edit()
        editor?.apply {
            remove(USER_SESSION)
            apply()
        }
    }
}