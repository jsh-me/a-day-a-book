package com.aday.core.dagger.module

import android.content.Context
import androidx.fragment.app.Fragment
import com.aday.core.dagger.annotation.qualifier.ForFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FragmentModule {
    @Provides
    @Singleton
    @ForFragment
    fun provideContext(fragment: Fragment): Context {
        return fragment.requireContext()
    }
}