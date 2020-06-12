package com.aday.abook.feature.main

import android.app.Activity
import com.aday.core.dagger.annotation.scope.ActivityScope
import com.aday.core.dagger.module.ActivityModule
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface MainComponent {
    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun activity(activity: Activity): Builder

        fun build(): MainComponent
    }

    fun inject(activity: MainActivity)
}