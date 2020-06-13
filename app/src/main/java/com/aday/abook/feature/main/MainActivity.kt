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
import com.aday.abook.feature.memo.BookMemoActivity
import com.aday.abook.feature.search.BookSearchActivity
import com.aday.core.consts.Consts
import com.aday.core.utils.loadUrl
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    private var selectedDate: String= ""
    private var mBookCoverImage: String= ""
    private var mBookName: String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectComponent()
        setupDatabinding()
        initView()
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

    private fun initView(){
        selectedDate = intent.getStringExtra(Consts.CALENDAR_DATE)?:""
    }

    private fun observeViewModel(){
        mViewModel.mAddBookClicked.observe(this, Observer {
            gotoBookSearch()
        })
        mViewModel.mDetailClicked.observe(this, Observer {
            gotoMemo()
        })
        mViewModel.mSaveButtonClicked.observe(this, Observer{
            mViewModel.saveData(selectedDate, mBookCoverImage, mBookName)
        })
    }

    private fun gotoBookSearch(){
        val intent = Intent(this, BookSearchActivity::class.java)
        startActivityForResult(intent, 1000)
    }
    private fun gotoMemo(){
        val intent = Intent(this, BookMemoActivity::class.java)
        startActivityForResult(intent, 2000)
        overridePendingTransition(R.anim.slide_up, R.anim.fade_out)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==1000 && resultCode==1000){
            mBookCoverImage = data?.getStringExtra(Consts.BOOK_IMAGE).toString()
            mBookName = data?.getStringExtra(Consts.BOOK_NAME).toString()
            bookCoverImage.loadUrl(mBookCoverImage)
            bookName.text = mBookName
            mViewModel.setSaveButton(true)
        }
    }
}