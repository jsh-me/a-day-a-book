package com.aday.core.customview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.aday.core.databinding.FragmentDialogBinding

class DialogAlertFragment: DialogFragment(){
    lateinit var mBinding: FragmentDialogBinding


    companion object{
        fun newInstance() = DialogAlertFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupDataBinding(inflater, container)
        initView()
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        dialog.window?.setLayout(width, height)
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?){
        mBinding = FragmentDialogBinding.inflate(inflater, container, false)
        mBinding.dialog = this@DialogAlertFragment
    }

    private fun initView(){
        dialog.window?.apply{
            requestFeature(Window.FEATURE_NO_TITLE)
        }
    }

    fun fixButton(){

    }

    fun shareButton(){

    }

    fun deleteButton(){

    }
}