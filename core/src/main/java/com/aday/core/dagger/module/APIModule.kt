package com.aday.core.dagger.module

import com.aday.core.consts.UrlConsts
import com.aday.core.dagger.annotation.qualifier.ForAccessToken
import com.aday.core.dagger.annotation.qualifier.api.ForUserAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class APIModule{

    @Provides
    @Singleton
    @ForUserAPI
    fun provideUserRetrofit(@ForAccessToken okHttpClient: OkHttpClient): Retrofit {
        val baseUrl: String = UrlConsts.BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
}