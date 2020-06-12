package com.aday.abook.feature.memo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aday.abook.mvvm.SingleLiveEvent
import javax.inject.Inject

class BookMemoViewModel @Inject constructor(application: Application): AndroidViewModel(application){

    val mCloseButtonClicked: SingleLiveEvent<Void> = SingleLiveEvent()

    fun close(){
        mCloseButtonClicked.call()
    }
}