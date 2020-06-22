package com.aday.core.dagger.module

import android.app.Application
import com.aday.core.dagger.annotation.qualifier.ForApplication
import com.aday.core.helper.SharedPreferenceRepository
import com.aday.core.helper.SharedPreferenceRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefsModule{
    @Provides
    @Singleton
    @ForApplication
    internal fun provideSharedPrefs(application: Application): SharedPreferenceRepository {
        return SharedPreferenceRepositoryImpl(application)
    }
}