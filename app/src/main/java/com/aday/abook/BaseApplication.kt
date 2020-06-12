package com.aday.abook

import android.app.Application
import timber.log.Timber

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        APP_COMPONENT = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        APP_COMPONENT.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        mInstance = this
    }

    companion object {
        private lateinit var APP_COMPONENT: ApplicationComponent
        private lateinit var mInstance: BaseApplication

        fun getAppComponent(): ApplicationComponent {
            return APP_COMPONENT
        }
        fun getInstance(): BaseApplication? {
            return mInstance
        }
    }
}