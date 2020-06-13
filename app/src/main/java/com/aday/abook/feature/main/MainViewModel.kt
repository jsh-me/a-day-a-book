package com.aday.abook.feature.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aday.abook.mvvm.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application){

    val mDetailClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    val mAddBookClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    val mAddWord: MutableLiveData<Boolean> = MutableLiveData()

    fun initData(){
        //RoomDB
    }

    fun addWordClicked(){
        mAddWord.value = true
    }

    fun addBookClicked(){
        mAddBookClicked.call()
    }

    fun detailClicked(){
        mDetailClicked.call()
    }

}