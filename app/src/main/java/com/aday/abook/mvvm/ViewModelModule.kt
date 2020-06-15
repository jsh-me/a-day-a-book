package com.aday.abook.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aday.abook.feature.calendar.CalendarViewModel
import com.aday.abook.feature.main.MainViewModel
import com.aday.abook.feature.memo.BookMemoViewModel
import com.aday.abook.feature.search.BookSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookMemoViewModel::class)
    internal abstract fun memoViewModel(viewModel: BookMemoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookSearchViewModel::class)
    internal abstract fun serachViewModel(viewModel: BookSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CalendarViewModel::class)
    internal abstract fun calendarViewMdel(viewModel: CalendarViewModel): ViewModel
}