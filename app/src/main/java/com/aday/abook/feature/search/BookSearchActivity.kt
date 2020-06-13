package com.aday.abook.feature.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aday.abook.BaseApplication
import com.aday.abook.R
import com.aday.abook.databinding.ActivitySearchBookListBinding
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

    private fun observeViewModel(){
        mViewModel.mSearchText.observe(this, Observer{

        })

        mViewModel.mBookListLoadFinished.observe(this, Observer {
            initRecyclerView()
        })
    }

    private fun initRecyclerView(){
        bookResultRecycler.adapter?.notifyDataSetChanged()
        bookResultRecycler.apply{
            layoutManager = LinearLayoutManager(this@BookSearchActivity)
            adapter = BookSearchAdapter(mViewModel.getBookList())
        }
    }
}