package com.aday.abook.feature.calendar

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aday.abook.BaseApplication
import com.aday.abook.R
import com.aday.abook.databinding.ActivityMainCalendarBinding
import com.aday.abook.feature.main.MainActivity
import com.aday.core.consts.Consts
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import kotlinx.android.synthetic.main.activity_main_calendar.*
import javax.inject.Inject

class CalendarActivity : AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivityMainCalendarBinding
    private lateinit var mViewModel: CalendarViewModel
    private var mList: ArrayList<CalendarDay> = ArrayList()
    private lateinit var mCalendarDay: CalendarDay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calendar)

        injectComponent()
        setupDataBinding()
        observeViewModel()
        initView()
    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .calendarComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }

    private fun setupDataBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_calendar)
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[CalendarViewModel::class.java]
    }

    private fun initView(){
        mViewModel.initView()
        //TODO: 현재 날짜 이후로 터치 금지
        mBinding.calendarView.state().edit()
            .setMaximumDate(CalendarDay.from(CalendarDay.today().year, CalendarDay.today().month, CalendarDay.today().day))
            .commit()
        mBinding.calendarView.setOnDateChangedListener { _, date, _ ->
            mCalendarDay = date
            val intent = Intent(this, MainActivity::class.java).apply{
                putExtra(Consts.CALENDAR_DATE, "${date.year},${date.month},${date.day}")
            }
            startActivityForResult(intent, 3000)
        }
    }

    private fun observeViewModel(){
        mViewModel.mLoadingFinished.observe(this , Observer {
            mBinding.calendarView.addDecorator(EventDecorator(Color.RED, mViewModel.getDateList()))
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 3000 && resultCode == 3000) {
            //mList.add(mCalendarDay)
          //  mBinding.calendarView.addDecorator(EventDecorator(Color.RED, mList))

        }
    }
}