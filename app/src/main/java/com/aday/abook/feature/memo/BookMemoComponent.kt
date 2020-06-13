package com.aday.abook.feature.memo

import android.app.Activity
import com.aday.core.dagger.annotation.scope.ActivityScope
import com.aday.core.dagger.module.ActivityModule
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface BookMemoComponent {
    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun activity(activity: Activity): Builder

        fun build(): BookMemoComponent
    }

    fun inject(activity: BookMemoActivity)
}