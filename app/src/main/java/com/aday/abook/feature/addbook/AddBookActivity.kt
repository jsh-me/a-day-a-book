package com.aday.abook.feature.addbook

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aday.abook.BaseApplication
import com.aday.abook.R
import com.aday.abook.databinding.ActivityAddBookBinding
import com.aday.abook.databinding.ActivityMainBinding
import com.aday.abook.feature.memo.BookMemoActivity
import com.aday.abook.feature.search.BookSearchActivity
import com.aday.core.consts.Consts
import com.aday.core.customview.DialogAlertFragment
import com.aday.core.utils.loadUrl
import com.aday.core.utils.loadUrlCenterCrop
import kotlinx.android.synthetic.main.activity_add_book.*
import javax.inject.Inject

class AddBookActivity : AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivityAddBookBinding
    private lateinit var mViewModel: AddBookViewModel

    private lateinit var dialog: DialogAlertFragment

    private var selectedDate: String= ""
    private var mBookCoverImage: String= ""
    private var mBookName: String= ""
    private var mRating: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectComponent()
        setupDatabinding()
        initView()
        observeViewModel()
    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .addBookComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }

    private fun setupDatabinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_book)
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[AddBookViewModel::class.java]
        mBinding.addBookViewModel = mViewModel
    }
    private fun initView(){
        selectedDate = intent.getStringExtra(Consts.CALENDAR_DATE)?:""
        mViewModel.loadData(selectedDate)
        mBinding.fiveWords.setOnEditorActionListener { _, actionId, _ ->
            when(actionId){
                EditorInfo.IME_ACTION_GO -> {
                    mViewModel.saveFiveWordsButtonClicked()
                }
            }
            true
        }
        mBinding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            mRating = rating
        }
    }

    private fun observeViewModel(){
        mViewModel.mBookCoverImage.observe(this, Observer {
            mBinding.bookCoverImage.background = getDrawable(R.drawable.image_back)
            mBinding.bookCoverImage.clipToOutline = true
            mBinding.bookCoverImage.elevation = 10f
            mBinding.bookCoverImage.loadUrlCenterCrop(it)
        })
        mViewModel.mBookName.observe(this, Observer {
            mBinding.bookName.text = it
        })
        mViewModel.mBookRating.observe(this, Observer {
            mBinding.ratingBar.rating = it
        })
        mViewModel.mFiveWords.observe(this, Observer {
            mBinding.fiveWords.setText(it)
        })

        mViewModel.mClickedDate.observe(this, Observer {
            mBinding.todayTextView.text = it
        })

        mViewModel.mAddBookClicked.observe(this, Observer {
            gotoBookSearch()
        })
        mViewModel.mDetailClicked.observe(this, Observer {
            gotoMemo()
        })
        mViewModel.isToday.observe(this, Observer {
            setDayText(it)
        })
        mViewModel.mBackButtonClicked.observe(this, Observer {
            finish()
        })

        mViewModel.mSaveButtonClicked.observe(this, Observer{
            mViewModel.saveData(selectedDate, mBookCoverImage, mBookName, mRating, fiveWords.text.toString())
            setResult(3000, intent.putExtra(Consts.CALENDAR_DATE, selectedDate))
            finish()
        })

        mViewModel.mMoreButtonClicked.observe(this, Observer{
            dialog = DialogAlertFragment.newInstance()
            if(::dialog.isInitialized){
                dialog.show(supportFragmentManager, "dialog")
            }

        })
    }

    private fun setDayText(isToday: Boolean){
        if(isToday) mBinding.whatBookTextView.text = resources.getText(R.string.what_book)
        else mBinding.whatBookTextView.text = resources.getText(R.string.what_book_past)
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
            mBinding.bookCoverImage.loadUrl(mBookCoverImage)
            mBinding.bookCoverImage.background = getDrawable(R.drawable.image_back)
            mBinding.bookCoverImage.clipToOutline = true
            mBinding.bookCoverImage.elevation = 10f
            mBinding.bookName.text = mBookName
            mViewModel.setSaveButton(true)
        }
    }
}