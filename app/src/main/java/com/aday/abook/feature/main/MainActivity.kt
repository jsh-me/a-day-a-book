package com.aday.abook.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aday.abook.BaseApplication
import com.aday.abook.R
import com.aday.abook.databinding.ActivityMainBinding
import com.aday.abook.feature.search.BookSearchActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectComponent()
        setupDatabinding()
        observeViewModel()
    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .mainComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }

    private fun setupDatabinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        mBinding.mainViewModel = mViewModel
    }

    private fun observeViewModel(){
        mViewModel.mAddBookClicked.observe(this, Observer {
            gotoBookSearch()
        })
        mViewModel.mDetailClicked.observe(this, Observer {
            gotoMemo()
        })
    }

    private fun gotoBookSearch(){
        val intent = Intent(this, BookSearchActivity::class.java)
        startActivityForResult(intent, 1000)
    }
    private fun gotoMemo(){
    }
}