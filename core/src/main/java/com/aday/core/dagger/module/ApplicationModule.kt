package com.aday.core.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.aday.core.dagger.annotation.qualifier.ForApplication
import com.aday.core.helper.SharedPreferenceHelper
import com.aday.core.helper.SharedPreferenceRepository
import com.aday.core.helper.SharedPreferenceRepositoryImpl
import com.facebook.stetho.inspector.domstorage.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    @ForApplication
    internal fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}