package com.aday.core.dagger.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.aday.core.dagger.annotation.qualifier.ForActivity
import com.aday.core.dagger.annotation.scope.ActivityScope
import dagger.Module
import dagger.Provides

//Module(공급) ---- Component(연결) ---- Inject(주입)
@Module
class ActivityModule{

    @Provides
    @ActivityScope
    @ForActivity
    fun provideContext(activity: AppCompatActivity): Context {
        return activity
    }
}