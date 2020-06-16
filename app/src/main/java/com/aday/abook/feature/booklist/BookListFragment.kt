package com.aday.abook.feature.booklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aday.abook.BaseApplication
import com.aday.abook.databinding.FragmentCalendarListBinding
import com.aday.abook.feature.main.MainViewModel
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

    }

}