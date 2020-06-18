package com.aday.abook.feature.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aday.abook.BaseApplication
import com.aday.abook.R
import com.aday.abook.databinding.ActivitySettingBinding
import javax.inject.Inject

class SettingActivity: AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mBinding: ActivitySettingBinding
    lateinit var settingAdapter: SettingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        setupDataBinding()
        initView()
    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .settingComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }

    private fun setupDataBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        mBinding.setting = this@SettingActivity
    }

    private fun initView(){
        settingAdapter = SettingAdapter(click())
        mBinding.settingRecycler.apply{
            layoutManager = LinearLayoutManager(this@SettingActivity)
            adapter = settingAdapter
        }
    }

    private fun click() = { pos: Int->
        when(pos){
            0 ->{
                //set password
            }
            1->{
                //go to play store
            }
        }
    }

    fun backButton(){
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}