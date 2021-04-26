package cl.gersard.covidexercise.data.remote.di

import cl.gersard.covidexercise.BuildConfig
import cl.gersard.covidexercise.data.remote.ApiConstants
import cl.gersard.covidexercise.data.remote.CovidApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesOkHttpClient() = OkHttpClient.Builder().apply {

        addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .addHeader(
                    ApiConstants.HEADER_RAPID_API_KEY,
                    BuildConfig.API_KEY
                ).build()
            chain.proceed(request)
        })
        connectTimeout(8, TimeUnit.SECONDS)
        readTimeout(8, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logInterceptor)
        }
    }.build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder().apply {
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(ApiConstants.URL_BASE)
        client(okHttpClient)
    }.build()

    @Provides
    @Singleton
    fun providesCovidService(retrofit: Retrofit) = retrofit.create(CovidApi::class.java)

}