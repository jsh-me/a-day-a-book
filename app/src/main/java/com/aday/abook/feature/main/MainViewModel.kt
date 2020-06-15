package com.aday.abook.feature.main

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aday.abook.mvvm.SingleLiveEvent
import com.aday.core.api.usecase.InsertBookListUseCase
import com.aday.model.room.BookListEntity
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application,
                                        private val insertBookListUseCase: InsertBookListUseCase) : AndroidViewModel(application){

    val mDetailClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    val mAddBookClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    val mAddWord: MutableLiveData<Boolean> = MutableLiveData()
    val mSaveButtonClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    val isSave: ObservableField<Boolean> = ObservableField(false)

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

    fun saveButtonClicked() {
        mSaveButtonClicked.call()
    }

    fun saveData(date: String, image: String, name: String){
        insertBookListUseCase.insert(BookListEntity(null, date, image,name, 5, null, null))
    }

    fun setSaveButton(b: Boolean){
        isSave.set(b)
    }

}