package com.aday.abook

import android.app.Application
import android.app.IntentService
import com.aday.core.dagger.module.APIModule
import com.aday.core.dagger.module.ApplicationModule
import com.aday.core.dagger.module.HttpClientModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class,
        HttpClientModule::class,
        APIModule::class
        ])

interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(baseApplication: BaseApplication)

    fun inject(intentService: IntentService)

    //add component builder


}