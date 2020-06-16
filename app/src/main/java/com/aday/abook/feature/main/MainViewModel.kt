package com.aday.abook.feature.main

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aday.abook.mvvm.SingleLiveEvent
import com.aday.core.api.usecase.LoadAllDateUseCase
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application,
                                        private val loadAllDatesUseCase: LoadAllDateUseCase): AndroidViewModel(application){

    var mLoadingFinished: SingleLiveEvent<Void> = SingleLiveEvent()
    private var mAllDateList: ArrayList<CalendarDay> = ArrayList()

    @SuppressLint("CheckResult")
    fun initView(){
        loadAllDatesUseCase.allLoadDates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val mListDate = it.map{ it.split(",") }
                mListDate.map{ date ->
                    mAllDateList.add(CalendarDay.from(date[0].toInt(), date[1].toInt(), date[2].toInt()))
                }
                mLoadingFinished.call()
            },{
                it.localizedMessage
            })
    }

    fun getDateList(): ArrayList<CalendarDay> {
        return mAllDateList
    }

}