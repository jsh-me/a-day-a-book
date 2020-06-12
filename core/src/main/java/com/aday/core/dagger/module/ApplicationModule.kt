package com.aday.core.dagger.module

import android.app.Application
import android.content.Context
import com.aday.core.dagger.annotation.qualifier.ForApplication
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