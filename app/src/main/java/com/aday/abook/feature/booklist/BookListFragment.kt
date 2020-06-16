package com.aday.abook.feature.booklist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aday.abook.BaseApplication
import com.aday.abook.databinding.FragmentCalendarListBinding
import com.aday.abook.feature.main.MainViewModel
import com.aday.model.room.BookListEntity
import javax.inject.Inject

class BookListFragment: Fragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: FragmentCalendarListBinding
    private lateinit var mViewModel: MainViewModel

    companion object{
        @JvmStatic
        fun newInstance() = BookListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectComponent()
        setupDataBinding(inflater, container)
        initVIew()
        observeViewModel()
        return mBinding.root
    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .bookListComponentBuilder()
            .fragment(this)
            .build()
            .inject(this)
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?){
        mBinding = FragmentCalendarListBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        mBinding.fragmentViewModel = mViewModel
    }
    private fun initVIew(){
        mViewModel.initBookListVIew()
    }

    private fun observeViewModel(){
        mViewModel.mBookListLoadingFinished.observe(this, Observer {
            mBinding.bookListRecycler.apply{
                layoutManager = LinearLayoutManager(activity)
                adapter = BookListAdapter(mViewModel.getAllBookDataList(), click())
            }
            mBinding.bookListRecycler.adapter?.notifyDataSetChanged()
        })
    }

    private fun click() = { myBook : BookListEntity ->
    }

}