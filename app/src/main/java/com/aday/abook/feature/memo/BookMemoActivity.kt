package com.aday.abook.feature.memo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aday.abook.BaseApplication
import com.aday.abook.R
import com.aday.abook.databinding.ActivityBookMemoBinding
import javax.inject.Inject

class BookMemoActivity: AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivityBookMemoBinding
    private lateinit var mViewModel: BookMemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        setupDatabinding()
        observeViewModel()
    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .bookMemoComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }

    private fun setupDatabinding(){
        mBinding =  DataBindingUtil.setContentView(this, R.layout.activity_book_memo)
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[BookMemoViewModel::class.java]
        mBinding.memoViewModel = mViewModel
    }

    private fun observeViewModel(){
        mViewModel.mCloseButtonClicked.observe(this, Observer{
            goBackMain()
        })
    }

    private fun goBackMain(){
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.slide_down)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.slide_down)

    }
}