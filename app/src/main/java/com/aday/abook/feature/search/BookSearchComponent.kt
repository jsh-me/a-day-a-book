package com.aday.abook.feature.search

import android.app.Activity
import com.aday.core.dagger.annotation.scope.ActivityScope
import com.aday.core.dagger.module.ActivityModule
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface BookSearchComponent{
    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun activity(activity: Activity): Builder

        fun build(): BookSearchComponent
    }

    fun inject(activity: BookSearchActivity)
}