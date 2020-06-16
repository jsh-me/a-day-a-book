package com.aday.abook.fragment.calendar

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aday.abook.BaseApplication
import com.aday.abook.databinding.FragmentCalendarViewBinding
import com.aday.abook.feature.addbook.AddBookActivity
import com.aday.abook.feature.main.MainViewModel
import com.aday.core.consts.Consts
import com.prolificinteractive.materialcalendarview.CalendarDay
import javax.inject.Inject

class CalendarFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: FragmentCalendarViewBinding
    private lateinit var mViewModel: MainViewModel
    private var mList: ArrayList<CalendarDay> = ArrayList()
    private lateinit var mCalendarDay: CalendarDay

    companion object{
        @JvmStatic
        fun newInstance() = CalendarFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectComponent()
        setupDataBinding(inflater, container)
        initView()
        observeViewModel()
        return mBinding.root
    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .calendarComponentBuilder()
            .fragment(this)
            .build()
            .inject(this)
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?){
        mBinding = FragmentCalendarViewBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        mBinding.fragmentViewModel = mViewModel
    }

    private fun initView(){
        mViewModel.initView()
        //TODO: 현재 날짜 이후로 터치 금지
        mBinding.calendarView.state().edit()
            .setMaximumDate(CalendarDay.from(CalendarDay.today().year, CalendarDay.today().month, CalendarDay.today().day))
            .commit()
        mBinding.calendarView.setOnDateChangedListener { _, date, _ ->
            mCalendarDay = date
            val intent = Intent(requireContext(), AddBookActivity::class.java).apply{
                putExtra(Consts.CALENDAR_DATE, "${date.year},${date.month},${date.day}")
            }
            startActivityForResult(intent, 3000)
        }
    }
    private fun observeViewModel(){
        mViewModel.mLoadingFinished.observe(this , Observer {
            mBinding.calendarView.addDecorator(
                EventDecorator(
                    Color.RED,
                    mViewModel.getDateList()
                )
            )
        })
    }
}