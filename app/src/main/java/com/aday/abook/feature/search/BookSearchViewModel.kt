package com.aday.abook.feature.search

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aday.abook.mvvm.SingleLiveEvent
import com.aday.core.api.usecase.GetSearchBookUseCase
import com.aday.core.utils.removeHttpTag
import com.aday.model.entity.BookInfo
import javax.inject.Inject

class BookSearchViewModel @Inject constructor(application: Application,
                                              private val getSearchBookUseCase: GetSearchBookUseCase): AndroidViewModel(application){

    val mSearchText: ObservableField<String> = ObservableField()
    val searchNoResult: ObservableField<Boolean> = ObservableField()
    private val mBookListInfo: ArrayList<BookInfo> = ArrayList()

    val mBookListLoadFinished: SingleLiveEvent<Void> = SingleLiveEvent()

    @SuppressLint("CheckResult")
    fun searchButtonClicked(){
        mBookListInfo.clear()
       // val encodeText = URLEncoder.encode(mSearchText.value!!, "UTF-8")
        getSearchBookUseCase.getSearchBook(mSearchText.get()!!)
            .subscribe({
                if(it.documents.isNotEmpty()) {
                    it.documents.map { book ->

                    mBookListInfo.add(BookInfo(book.authors, book.contents,
                            book.publisher.removeHttpTag(), book.title.removeHttpTag(), book.thumbnail))
                    }

                    searchNoResult.set(false)
                    mBookListLoadFinished.call()
                }
                else {
                    searchNoResult.set(true)
                }
            },{
                it.localizedMessage
            })
    }

    fun clearWordButtonClicked(){
        mSearchText.set("")
    }

    fun getBookList(): ArrayList<BookInfo> {
        return mBookListInfo
    }

}