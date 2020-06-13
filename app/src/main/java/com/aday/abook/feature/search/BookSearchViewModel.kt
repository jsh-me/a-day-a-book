package com.aday.abook.feature.search

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aday.abook.mvvm.SingleLiveEvent
import com.aday.core.api.usecase.GetSearchBookUseCase
import com.aday.model.entity.BookInfo
import java.net.URLEncoder
import javax.inject.Inject

class BookSearchViewModel @Inject constructor(application: Application,
                                              private val getSearchBookUseCase: GetSearchBookUseCase): AndroidViewModel(application){

    val mSearchText: MutableLiveData<String> = MutableLiveData()
    val mBookListInfo: ArrayList<BookInfo> = ArrayList()

    val mBookListLoadFinished: SingleLiveEvent<Void> = SingleLiveEvent()

    @SuppressLint("CheckResult")
    fun searchButtonClicked(){
        mBookListInfo.clear()
        val encodeText = URLEncoder.encode(mSearchText.value!!, "UTF-8")
        getSearchBookUseCase.getSearchBook(encodeText)
            .subscribe({
                it.items.map{
                    mBookListInfo.add(BookInfo(it.title, it.author, it.publisher, it.image))
                }
                mBookListLoadFinished.call()
            },{
                it.localizedMessage
            })
    }

    fun getBookList(): ArrayList<BookInfo> {
        return mBookListInfo
    }

}