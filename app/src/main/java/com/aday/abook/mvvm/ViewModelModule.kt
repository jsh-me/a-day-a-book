package com.aday.abook.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aday.abook.feature.main.MainViewModel
import com.aday.abook.feature.addbook.AddBookViewModel
import com.aday.abook.feature.memo.BookMemoViewModel
import com.aday.abook.feature.pwd.PwdViewModel
import com.aday.abook.feature.search.BookSearchViewModel
import com.aday.abook.feature.setting.SettingComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AddBookViewModel::class)
    internal abstract fun addBookViewModel(viewModel: AddBookViewModel): ViewModel

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
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewMdel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PwdViewModel::class)
    internal abstract fun pwdViewModel(viewModel: PwdViewModel): ViewModel

}