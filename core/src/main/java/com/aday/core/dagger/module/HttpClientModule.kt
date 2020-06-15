package com.aday.core.dagger.module

import com.aday.core.BuildConfig
import com.aday.core.consts.Consts
import com.aday.core.dagger.annotation.qualifier.ForAccessToken
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class HttpClientModule {
    @Provides
    @Singleton
    @ForAccessToken
    fun provideOkHttpClientForAccessToken(): OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.addNetworkInterceptor(StethoInterceptor())
            .addInterceptor { chain ->
                val auth = Consts.auth
               var request = chain.request()
                   .newBuilder()
                   .addHeader("Authorization", auth)
                   .build()

                chain.proceed(request)
            }
        // log
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        // timeout
        builder.readTimeout(1, TimeUnit.MINUTES)
        builder.connectTimeout(30, TimeUnit.SECONDS)

        return builder.build()
    }
}