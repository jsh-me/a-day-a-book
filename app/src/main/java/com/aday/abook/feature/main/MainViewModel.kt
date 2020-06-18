package com.aday.abook.feature.main

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aday.abook.mvvm.SingleLiveEvent
import com.aday.core.api.usecase.GetAllDataUseCase
import com.aday.core.api.usecase.LoadAllDateUseCase
import com.aday.model.room.BookListEntity
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application,
                                        private val loadAllDateUseCase: LoadAllDateUseCase,
                                        private val getAllDataUseCase: GetAllDataUseCase): AndroidViewModel(application){

    var mLoadingFinished: SingleLiveEvent<Void> = SingleLiveEvent()
    var mBookListLoadingFinished: SingleLiveEvent<Void> = SingleLiveEvent()
    var mSettingButtonClicked: SingleLiveEvent<Void> = SingleLiveEvent()
    var mAllDateList: ArrayList<CalendarDay> = ArrayList()
    var mAllBookDataList: ArrayList<BookListEntity> = ArrayList()

    @SuppressLint("CheckResult")
    fun initCalendarView(){
        mAllDateList.clear()
        loadAllDateUseCase.allLoadDates()
            .filter{it.isNotEmpty()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val mListDate = it.map{ it.split(",") }
                mListDate.map{ date ->
                    mAllDateList.add(CalendarDay.from(date[0].toInt(), date[1].toInt(), date[2].toInt()))
                }
                mLoadingFinished.call()
            },{
                Timber.e(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    fun initBookListVIew(){
        mAllBookDataList.clear()
        getAllDataUseCase.getAllData()
            .filter{it.isNotEmpty()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.map{ entity ->
                    mAllBookDataList.add(entity)
                }
                mBookListLoadingFinished.call()
            },{
                Timber.e(it.localizedMessage)
            })
    }

    fun getDateList(): ArrayList<CalendarDay> {
        return mAllDateList
    }

    fun getAllBookDataList(): ArrayList<BookListEntity> {
        return mAllBookDataList
    }

    fun settingButtonClicked(){
        mSettingButtonClicked.call()
    }
}