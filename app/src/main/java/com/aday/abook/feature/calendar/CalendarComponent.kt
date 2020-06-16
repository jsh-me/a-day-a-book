package com.aday.abook.feature.calendar

import androidx.fragment.app.Fragment
import com.aday.core.dagger.annotation.scope.FragmentScope
import com.aday.core.dagger.module.FragmentModule
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface CalendarComponent {
    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun fragment(fragment: Fragment): Builder

        fun build(): CalendarComponent
    }

    fun inject(fragment: CalendarFragment)
}