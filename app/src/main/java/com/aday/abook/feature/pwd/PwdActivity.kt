package com.aday.abook.feature.pwd

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aday.abook.BaseApplication
import com.aday.abook.databinding.ActivitySettingPasswordBinding
import com.poovam.pinedittextfield.PinField
import javax.inject.Inject

class PwdActivity: AppCompatActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivitySettingPasswordBinding
    private lateinit var mViewModel: PwdViewModel
    private var mPassword = ""
    private var mInputFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
        setupDataBinding()
        observeViewModel()
        initView()
    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .pwdComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }

    private fun setupDataBinding(){
        mBinding = DataBindingUtil.setContentView(this, com.aday.abook.R.layout.activity_setting_password)
        mViewModel = ViewModelProviders.of(this, viewModelFactory)[PwdViewModel::class.java]
        mBinding.viewModel = mViewModel
    }

    private fun observeViewModel(){

    }

    private fun initView() {
        //Todo: 분리해야함
        val listener = object : PinField.OnTextCompleteListener{
            override fun onTextComplete(enteredText: String): Boolean {
                //Toast.makeText(this@PwdActivity,enteredText, Toast.LENGTH_SHORT).show()
                if(!mInputFlag) {
                    mPassword = enteredText
                    mBinding.lineField.text?.clear()
                    Toast.makeText(applicationContext, "비밀번호를 다시 한 번 입력해주세요.", Toast.LENGTH_SHORT).show()
                    mInputFlag = true
                } else {
                    if(mPassword == enteredText) {
                        Toast.makeText(applicationContext, "비밀번호가 설정되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "비밀번호가 일치하지 않습니다.\n다시 한 번 입력해주세요.", Toast.LENGTH_SHORT).show()
                        mBinding.lineField.text?.clear()
                    }
                }
                return false
            }
        }
        mBinding.lineField.onTextCompleteListener = listener
    }
}
