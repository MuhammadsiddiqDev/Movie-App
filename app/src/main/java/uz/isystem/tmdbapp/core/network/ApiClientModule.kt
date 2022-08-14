package uz.isystem.tmdbapp.core.network
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uz.isystem.tmdbapp.BuildConfig
import uz.isystem.tmdbapp.core.network.networkServices.*
import java.util.concurrent.TimeUnit

object ApiClientModule {

    val apiKey: String = "d02cdeadc6b1c5bc6983dec8513d4c87"

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor {
            Timber.d("tmd ##  %s", it)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


    private fun interceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val builder: Request.Builder = request.newBuilder()
            builder
                .header("Connection", "close")
//                .addHeader("language", preferenceUtil.language)
                .addHeader("Content-Type", "application/json")
//                .addHeader("Authorization", "Bearer ${preferenceUtil.accessToken}")
            val response = chain.proceed(builder.build())


            when (response.code) {
                401 -> {

                }

                404 -> {

                }

                403 -> {

                }

                500 -> {

                }

            }

            response
        }
    }


    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(getHttpLoggingInterceptor())
//            .addInterceptor(getChuckerInterceptor())
            .addInterceptor(interceptor())
            .build()
    }


//    private fun getChuckerCollector(): ChuckerCollector {
//        return ChuckerCollector(
//            context = App.instance!!,
//            showNotification = true,
//            retentionPeriod = RetentionManager.Period.FOREVER
//        )
//    }

//    private fun getChuckerInterceptor(): ChuckerInterceptor {
//        return ChuckerInterceptor.Builder(App.instance!!)
//            .collector(getChuckerCollector())
//            .maxContentLength(250_000L)
//            .alwaysReadResponseBody(true)
//            .build()
//
//    }
//    private fun getChuckerCollector(): ChuckerCollector {
//        return ChuckerCollector(
//            context = App.instance!!,
//            showNotification = true,
//            retentionPeriod = RetentionManager.Period.FOREVER
//        )
//    }
//    private fun getChuckerInterceptor(): ChuckerInterceptor {
//        return ChuckerInterceptor.Builder(App.instance!!)
//            .collector(getChuckerCollector())
//            .maxContentLength(250_000L)
//            .alwaysReadResponseBody(true)
//            .build()
//
//    }


    private fun getApiClient(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .build()
    }

    private fun getGson(): Gson = GsonBuilder().setLenient().create()

    fun getLoginService(): LoginServices {
        return ApiClientModule.getApiClient().create(LoginServices::class.java)
    }

    fun getMovieService(): MovieServices {
        return ApiClientModule.getApiClient().create(MovieServices::class.java)
    }

    fun getMoviesData(): MovieDataServices {
        return ApiClientModule.getApiClient().create(MovieDataServices::class.java)
    }

    fun getActorData(): ActorDataServices {
        return ApiClientModule.getApiClient().create(ActorDataServices::class.java)
    }

    fun getSearchQueryService(): SearchService {
        return ApiClientModule.getApiClient().create(SearchService::class.java)
    }
}