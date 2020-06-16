package com.aday.abook.feature.main

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aday.abook.mvvm.SingleLiveEvent
import com.aday.core.api.usecase.GetBookListByDateUseCase
import com.aday.core.api.usecase.InsertBookListUseCase
import com.aday.model.room.BookListEntity
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application,
                                        private val insertBookListUseCase: InsertBookListUseCase,
                                        private val getBookListByDateUseCase: GetBookListByDateUseCase) : AndroidViewModel(application){

    val mDetailClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    val mAddBookClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    val mSaveButtonClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    val isSave: ObservableField<Boolean> = ObservableField(false)
    val isFocusableText : ObservableField<Boolean> = ObservableField()
    val mBackButtonClicked: SingleLiveEvent<Void> = SingleLiveEvent()


    var mBookCoverImage: MutableLiveData<String> = MutableLiveData()
    var mBookName: MutableLiveData<String> = MutableLiveData()
    var mBookMemo: MutableLiveData<String> = MutableLiveData()
    var mBookRating: MutableLiveData<Float> = MutableLiveData()
    var mFiveWords: MutableLiveData<String> = MutableLiveData()

    var mClickedDate: MutableLiveData<String> = MutableLiveData()
    var isToday: MutableLiveData<Boolean> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun loadData(selectedDate: String){
        //RoomDB
        getBookListByDateUseCase.getBookListByDate(selectedDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mBookCoverImage.value = it.image
                mBookName.value = it.name
                mBookMemo.value = it.memo
                mBookRating.value = it.rating
                mFiveWords.value = it.fiveWords
            },{
                it.localizedMessage
            })
        mClickedDate.value = "${selectedDate.split(",")[1]}월 ${selectedDate.split(",")[2]}일 이에요."
        isClickedDayToday(selectedDate)
    }

    private fun isClickedDayToday(selectedDate: String){
        val mToday = "${CalendarDay.today().year},${CalendarDay.today().month},${CalendarDay.today().day}"
        isToday.value = mToday == selectedDate
    }

    fun backButtonClicked(){
        mBackButtonClicked.call()
    }

    fun fixWordClicked(){
        isFocusableText.set(true)
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

    fun saveData(date: String, image: String, name: String, rating: Float, fiveWords: String){
        insertBookListUseCase.insert(BookListEntity(null, date, image,name, rating, fiveWords, null))
    }

    fun setSaveButton(b: Boolean){
        isSave.set(b)
    }

    fun saveFiveWordsButtonClicked() {
        isFocusableText.set(false)
    }

}