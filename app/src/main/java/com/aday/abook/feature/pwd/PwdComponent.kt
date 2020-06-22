package com.aday.abook.feature.pwd

import android.app.Activity
import com.aday.core.dagger.annotation.scope.ActivityScope
import com.aday.core.dagger.module.ActivityModule
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface PwdComponent{
    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun activity(activity: Activity): Builder

        fun build(): PwdComponent
    }

    fun inject(activity: PwdActivity)
}