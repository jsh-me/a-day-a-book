package com.aday.abook.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aday.abook.BaseApplication
import com.aday.abook.R

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectComponent()

    }

    private fun injectComponent(){
        BaseApplication.getAppComponent()
            .mainComponentBuilder()
            .activity(this)
            .build()
            .inject(this)
    }
}