package com.aday.abook.feature.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aday.abook.BaseApplication
import com.aday.abook.R
import com.aday.abook.databinding.ActivitySearchBookListBinding
import com.aday.core.consts.Consts
import com.aday.model.entity.BookInfo
import kotlinx.android.synthetic.main.activity_search_book_list.*
import javax.inject.Inject

class BookSearchActivity : AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivitySearchBookListBinding
    private lateinit var mViewModel: BookSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectComponent()
        setupDataBinding()
        setSearchBar()
        observeViewModel()
    }

    private fun injectComponent(){
     BaseApplication.getAppComponent()
         .bookSearchComponentBuilder()
         .activity(this)
         .build()
         .inject(this)
    }

    private fun setupDataBinding(){
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_book_list)
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[BookSearchViewModel::class.java]
        mBinding.searchViewModel = mViewModel
    }

    private fun setSearchBar(){
         mBinding.editSearchName.setOnEditorActionListener { _, actionId, _ ->
            when(actionId){
                EditorInfo.IME_ACTION_SEARCH -> {
                    mViewModel.searchButtonClicked()
                }
            }
            true
        }
        mBinding.editSearchName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrEmpty()) mBinding.clearWordButton.visibility = View.GONE
                else  mBinding.clearWordButton.visibility = View.VISIBLE
            }
        })
    }

    private fun observeViewModel(){
        mViewModel.mBookListLoadFinished.observe(this, Observer {
            initRecyclerView()
        })
    }

    private fun initRecyclerView(){
        mBinding.bookResultRecycler.adapter?.notifyDataSetChanged()
        mBinding.bookResultRecycler.apply{
            layoutManager = LinearLayoutManager(this@BookSearchActivity)
            adapter = BookSearchAdapter(mViewModel.getBookList(), click())
        }
    }

    private fun click() = { info :BookInfo ->
        val intent = Intent()
        intent.apply{
            putExtra(Consts.BOOK_IMAGE, info.thumbnail)
            putExtra(Consts.BOOK_NAME, info.title)
        }
        setResult(1000, intent)
        finish()
    }
}