package com.aday.abook.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aday.abook.BaseApplication
import com.aday.abook.R
import com.aday.abook.databinding.ActivityMainBinding
import com.aday.abook.feature.calendar.CalendarFragment
import com.aday.abook.feature.booklist.BookListFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        setupDataBinding()
        initFragment()
        initView()
    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .mainComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }

    private fun setupDataBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        mBinding.mainViewModel = mViewModel
    }

    private fun initFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, CalendarFragment.newInstance()).commit()
    }

    private fun initView(){
        mBinding.segmentedButton {
            initWithItems { listOf(resources.getString(R.string.see_calendar), resources.getString(R.string.see_list)) }
            initialCheckedIndex = 0 }
        mBinding.segmentedButton.onSegmentChecked {
            when(it.text){
                resources.getString(R.string.see_calendar) -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, CalendarFragment.newInstance()).commit()
                }
                resources.getString(R.string.see_list) -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, BookListFragment.newInstance()).commit()
                }
            }
        }
    }
}