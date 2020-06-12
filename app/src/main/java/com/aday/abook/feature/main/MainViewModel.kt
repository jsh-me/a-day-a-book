package com.aday.abook.feature.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aday.core.api.usecase.GetSearchBookUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application,
                                        private val getSearchBookUseCase: GetSearchBookUseCase) : AndroidViewModel(application)